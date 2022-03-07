package pt.iiq.custom.util;

import openconnector.Item;
import openconnector.Item.Operation;
import pt.iiq.custom.entity.Role;
import pt.iiq.custom.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class SailpointAccountUtils {

    private SailpointAccountUtils() {
        throw new IllegalStateException("Esta classe È apenas para utilidade, n„o √© necess√°rio instanci√°-la");
    }

    /**
     * 
     * @param users
     * @return mapa com os utiliadores no formato que o IIQ espera
     */
    public static Map<String, Map<String, Object>> parseUserParameterToAccountMap(List<User> users) {

        Map<String, Map<String, Object>> accounts = new HashMap<>();

        for (User user : users) {
            accounts.put(user.getUserName(), SailpointAccountUtils.parseUserParameterToAccountMap(user));
        }

        return accounts;
    }

    public static Map<String, Map<String, Object>> parseGroupToGroupMap(List<Role> groups){

        Map<String, Map<String, Object>> groupMap = new HashMap<>();

        for(Role group : groups){
            groupMap.put(group.getName(), parseGroupToGroupMap(group));
        }

        return groupMap;
    }

    public static Map<String, Object> parseGroupToGroupMap(Role group) {
    	Map<String, Object> grp = new HashMap<>();
        grp.put(Constants.ATTR_ROLE, group.getName());

        return grp;
    }

    private static Map<String, Object> parseUserParameterToAccountMap(User user) {

        return SailpointAccountUtils.createAccountMap(user);
    }

    /**
     * MÈtodo que transforma a entidade no formato que o IIQ espera
     * @param user
     * @return
     */
    public static Map<String, Object> createAccountMap(User user) {
    	
    	try {
	        Map<String, Object> acct = new HashMap<>();
	        acct.put(Constants.ATTR_NAME, user.getUserName());
	        acct.put(Constants.ATTR_USERNAME, user.getUserName());
	        acct.put(Constants.ATTR_INACTIVE, user.getStatus() ? "False" : "True");
	        acct.put(Constants.ATTR_EMAIL, user.getEmail());
	        acct.put(Constants.ATTR_PASSWORD, user.getPassword());
	        acct.put(Constants.ATTR_FIRST_NAME, user.getFirstName());
	        acct.put(Constants.ATTR_LAST_NAME, user.getLastName());
	
	        return acct;
    	}catch (NullPointerException e) {
			return null;
		}
    }

    /**
     * MÈtodo que recebe o par‚metro no formato do IIQ e transforma em Entidade
     * @param items
     * @return
     */
    public static User mapUserParameterObject(List<Item> items) {

    	User user = new User();

        user.setUserName(SailpointAccountUtils.collectFieldValueFromItemList(items, Constants.ATTR_NAME));
        user.setEmail(SailpointAccountUtils.collectFieldValueFromItemList(items, Constants.ATTR_EMAIL));
        user.setFirstName(SailpointAccountUtils.collectFieldValueFromItemList(items, Constants.ATTR_FIRST_NAME));
        user.setRole(SailpointAccountUtils.collectFieldValueFromItemList(items, Constants.ATTR_ROLE));
        user.setPassword(SailpointAccountUtils.collectFieldValueFromItemList(items, Constants.ATTR_PASSWORD));
        user.setLastName(SailpointAccountUtils.collectFieldValueFromItemList(items, Constants.ATTR_LAST_NAME));
        
        return user;
    }


    private static String collectFieldValueFromItemList(List<Item> items, String fieldName) {
    	try {
	        return (String) items.stream()
	                .filter(item -> fieldName.equals(item.getName()))
	                .map(Item::getValue)
	                .findAny()
	                .orElse("");
	    }catch(NumberFormatException nf) {
	    	return "";
	    }  
    }
    private static List<Object> collectFieldListFromItemList(List<Item> items, String fieldName) {
    	
    	return items.stream()
    			.filter(item -> fieldName.equals(item.getName()))
    			.map(Item::getValue)
    			.collect(Collectors.toList());
    }
    
    public static List<String> mapGroupParameterObject(List<Item> items, Operation op) {
    	
    	List<Object> listaRoles = SailpointAccountUtils.collectFieldListFromItemList(items, Constants.ATTR_ROLE, op);
        List<String> roles= new ArrayList<>();
        
        for(Object obj : listaRoles) {

        	String role = String.valueOf(obj);
        	roles.add(role );
        }
        
        return roles;
    }
    
    private static List<Object> collectFieldListFromItemList(List<Item> items, String fieldName, Operation op) {
    	
    	return items.stream()
    			.filter(item -> fieldName.equals(item.getName()))
    			.filter(item -> op.equals(item.getOperation()))
    			.map(Item::getValue)
    			.collect(Collectors.toList());
    }
    

}
