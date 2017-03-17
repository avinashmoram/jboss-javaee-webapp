package org.jboss.tools.examples.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

@Named("contexts")
@GroupedConversationScoped
public class Contexts implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@Context
	private Map<String,Object> conversationContext=new HashMap<>();

	public void set(String key, Object value){
		conversationContext.put(key, value);
	}

	public void set(String key, boolean value){
		conversationContext.put(key, new Boolean(value));
	}

	public void set(String key, Long value){
		conversationContext.put(key, value);
	}

	public void set(String key, String value){
		conversationContext.put(key, value);
	}

	public Object get(String key){
		return conversationContext.get(key);
	}

	public String getString(String key){
		Object obj=conversationContext.get(key);
		if(obj instanceof String){
			return (String)obj;
		}

		return null;
	}


	public boolean getBoolean(String key){
		Object obj=conversationContext.get(key);
		if(obj instanceof Boolean){
			return ((Boolean)obj);
		}
		return false;
	}

	public Long getLong(String key){
		Object obj=conversationContext.get(key);
		if(obj instanceof Long){
			return ((Long)obj);
		}
		return 0l;
	}

	public void clear(){
		conversationContext.clear();
	}
}
