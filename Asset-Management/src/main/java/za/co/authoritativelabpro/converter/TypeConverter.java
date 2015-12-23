package za.co.authoritativelabpro.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.svenson.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TypeConverter<T> {
    
    final Class<T> typeParameterClass;
    
    public TypeConverter(Class<T> typeParameterClass) {
	this.typeParameterClass = typeParameterClass;
    }
    
    public List<T> convert(final String json) {
	List<T> types = new ArrayList<T>();
	
	JSONParser parser = new JSONParser();
	
	parser.addTypeHint("Types[]", typeParameterClass);
	
	@SuppressWarnings("unchecked")
	Map<String, List<T>> results1 = parser.parse(Map.class, json);
	
	for (Entry<String, List<T>> entry : results1.entrySet()) {
	    
	    TypeToken<List<T>> tocken = new TypeToken<List<T>>() {};
	    types = new Gson().fromJson(entry.getValue().toString(), tocken.getType());
	}
	return types;
    }
}
