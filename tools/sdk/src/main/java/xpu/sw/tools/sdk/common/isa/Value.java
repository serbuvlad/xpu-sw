//-------------------------------------------------------------------------------------
package xpu.sw.tools.sdk.common.isa;
//-------------------------------------------------------------------------------------
import java.io.*;
import java.util.*;

import org.apache.lucene.util.*;
import org.apache.commons.lang3.*;
import org.apache.logging.log4j.*;

import xpu.sw.tools.sdk.common.context.arch.*;
import xpu.sw.tools.sdk.asm.parser.*;

//-------------------------------------------------------------------------------------
public class Value extends Field {
    private Callable callable;
    private Instruction instruction;
    private int[][] dimensions;
    private String[] argumentReferences;
    private String argumentUnresolvedValuesLabel;
    private Expression argumentUnresolvedValuesExpression;

    private int[] argumentResolvedValues;


//-------------------------------------------------------------------------------------
    public Value() {
        this(null);
    }

//-------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

/*
//-------------------------------------------------------------------------------------
    public Value(String _valueString, int _valueNumber) {
        super(_valueString, _valueNumber);
    }

//-------------------------------------------------------------------------------------
    public Value(int _value) {
        super("const", _value);
    }
*/
//-------------------------------------------------------------------------------------
    public Value(int[][] _dimensions) {
        super("const", 0);
        dimensions = _dimensions;
    }

//-------------------------------------------------------------------------------------
    public Value copyOf(){
        Value _value = new Value();
        _value.callable = callable;
        _value.instruction = instruction;
        if(dimensions != null){
            _value.dimensions = Arrays.stream(dimensions).map(int[]::clone).toArray(int[][]::new);
        }
        if(argumentReferences != null){
            _value.argumentReferences = Arrays.stream(argumentReferences).toArray(String[]::new);
        }
        _value.argumentUnresolvedValuesLabel = argumentUnresolvedValuesLabel;
        _value.argumentUnresolvedValuesExpression = argumentUnresolvedValuesExpression;
        return _value;
    }
/*
//-------------------------------------------------------------------------------------
    public void replaceParametersWithExpressions(List<String> _parameters, List<AsmParser.ExpressionContext>  _expressions) {
//        if(argumentValues != null){
            for(int i = 0; i < argumentValues.length; i++){
                String _tmp = argumentValues[i];
                int _indexOfParameter = _parameters.indexOf(argumentValues[i]);
                if(_indexOfParameter != -1){
                    argumentValues[i] = getStringFromExpression(_expressions.get(_indexOfParameter));
//                    System.out.println("_parameter " + _tmp + "("+i+") replaced with " + argumentValues[i]);
                }
            }            
//        }
    }
*/
//-------------------------------------------------------------------------------------
    public void setArgumentReferences(String[] _argumentReferences){
        argumentReferences = _argumentReferences;
    }

//-------------------------------------------------------------------------------------
    public void setArgumentValues(String _label, AsmParser.ExpressionContext _expression, Callable _callable){
        argumentUnresolvedValuesLabel = _label;
        argumentUnresolvedValuesExpression = new Expression(_callable, _expression);
        callable = _callable;
    }

//-------------------------------------------------------------------------------------
    public boolean link(Instruction _instruction) {
        instruction = _instruction;
        return true;
    }

//-------------------------------------------------------------------------------------
    public boolean resolve() {
//                System.out.println("search for:["+opcode.getName()+"][" + value.getName()+"]");
        argumentResolvedValues = new int[argumentReferences.length];
        for (int i = 0; i < argumentReferences.length; i++) {
            argumentResolvedValues[i] = resolve(argumentReferences[i]);
//                System.out.println("i=" + i +", argumentReferences="+argumentReferences[i]+",value=[" + values[i]+"]");
        }
//        System.exit(0);
        return true;
    }

//-------------------------------------------------------------------------------------
    private int resolve(String _argumentReference) {
//        System.out.println(">>>>get [" + _argumentReference + "]");
/*        if(argumentValues == null){
            return -1;
        }*/
        switch (_argumentReference) {
            case "ZERO" : {
                return 0;
            }    
            case "ARG0:LABEL" : 
            case "ARG1:LABEL" :  {
                int _arg = resolveLabel(argumentUnresolvedValuesLabel);
                return _arg;
            }
            case "ARG0:NUMBER" : 
            case "ARG1:NUMBER" : {
                int _arg = argumentUnresolvedValuesExpression.resolve();
                return _arg;
            }    
            case "ARG0:NUMBER - 1" : {
                int _arg = argumentUnresolvedValuesExpression.resolve();
                return _arg - 1;
            }    
            case "DATA_SIZE – ARG0:NUMBER – 1" : {
                int _arg = argumentUnresolvedValuesExpression.resolve();
                int _dataSize = callable.getArchitectureImplementation().get("DATA_SIZE");
                return _dataSize - _arg - 1;
            }    
            default : {
                return callable.getArchitectureImplementation().get(_argumentReference);
            }    
        }
    }

//-------------------------------------------------------------------------------------
    private int resolveLabel(String _label) {
        int _labelAddress = callable.getByLabel(_label);
        int _currentAddress = instruction.getAddress();
        int _address = _labelAddress - _currentAddress;
//        System.out.println("Value. getArgFromLabel["+_label+"]: _labelAddress= "+_labelAddress + ", _currentAddress="+_currentAddress +", _address="+_address);
        return _address;
    }
    

//-------------------------------------------------------------------------------------
    public void pack(int _width) {
        data = new FixedBitSet(_width);
//        int _data = intdata;
//        int _indexInData;
        for(int i = 0; i < dimensions.length; i++){
            int[] _dimensions = dimensions[i];
            int _value = argumentResolvedValues[i];
//            System.out.println("pack:_dimensions[0]"+_dimensions[0]+", _dimensions[1]="+_dimensions[1]+", _dimensions[2]="+_dimensions[2] +", _value="+_value);
            for(int j = _dimensions[1]; j <= _dimensions[2]; j++){
                pack(j, _value, j - _dimensions[1]);
            }
//            System.out.println("after pack:"+dump());
//            System.exit(0);
        }
    }
    
//-------------------------------------------------------------------------------------
}
//-------------------------------------------------------------------------------------
