package io.ravitej.config;
/*
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by ravit on 05/12/2016.
 *//*

public class PermittedSettingsValidatingItem<T> {
    private String _value;
    private String _constrainingTypeName;
    private Class<T> _constrainingType;

    /// <summary>
    /// Default constructor
    /// </summary>
    public PermittedSettingsValidatingItem()
    {
        permittedValues = new ArrayList<String>();
        _constrainingType = T.class;
        ConstrainingTypeName = _constrainingType.getTypeName();
    }

    /// <summary>
    /// Constructor and assignment
    /// </summary>
    /// <param name="value"></param>
    public PermittedSettingsValidatingItem(String value)
    {
        this();
        Value = value;
    }

    /// <summary>
    /// Gets or sets the value.
    /// During the set, it checks the permitted list to ensure it is valid
    /// </summary>
    public String Value;

    public String getValue() { return _value; }

    public void setValue(String value){
        // if there any values, do the checking
            if (this.permittedValues.size() > 0)
            {
                if (!permittedValues.exists(s => s == value))
                {
                    throw new ArgumentOutOfRangeException(
                            $"The specified value '{value}' is not permitted for this item.  Please ensure it falls within the PermittedValues list of '{string.Join(",", PermittedValues)}'");
                }
            }

            _value = value;
        }

    /// <summary>
    /// Gets the enumerator version of the string
    /// </summary>
    //[IgnoreDataMember]
    //public T EnumValue => ExecutionSettings.ToEnum<T>(Value);

    /// <summary>
    /// The type to be validated against
    /// </summary>
    public String ConstrainingTypeName;

    public String getConstrainingTypeName() {
        return _constrainingTypeName;
    }

    public void setContrainingTypeName(String value){
            _constrainingTypeName = value;

            permittedValues.clear();

            if (_constrainingType != null)
            {
                foreach (object enumValue in Enum.GetValues(_constrainingType))
                {
                    permittedValues.add(enumValue.ToString());
                }

                // as, during deserialization it is possible to assign the value before the type-name, validate it here.
                if (Value != null && !StringUtils.containsWhitespace(Value))
                {
                    if (!permittedValues.Exists(s => s == Value))
                    {
                        throw new ArgumentOutOfRangeException(
                                $"The specified value '{Value}' is not permitted for this item.  Please ensure it falls within the PermittedValues list");
                    }
                }
            }
        }
    }

    /// <summary>
    /// Values that are valid
    /// </summary>
        //[IgnoreDataMember]
    private List<String> permittedValues;

    public List<String> getPermittedValues() { return permittedValues;}
}
*/
