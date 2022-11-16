package paulohenrique.rest.controllers;

//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import paulohenrique.rest.Math.SimpleMath;
import paulohenrique.rest.exceptions.UnsupportedMathOperationException;
import paulohenrique.rest.util.MathUtil;

@RestController
public class MathController {
	//private final AtomicLong counter = new AtomicLong();
	private SimpleMath math = new SimpleMath();
	@RequestMapping( value = "sum/{numberOne}/{numberTwo}", method = RequestMethod.GET )
	public Double sum(
		@PathVariable(value ="numberOne") String numberOne,
		@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception{
		if(!MathUtil.isNumeric(numberOne) || !MathUtil.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.sum(MathUtil.convertToDouble(numberOne), MathUtil.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) throws Exception{
		if(!MathUtil.isNumeric(numberOne) || !MathUtil.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.subtraction(MathUtil.convertToDouble(numberOne), MathUtil.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "mul/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mul(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) {
		if(!MathUtil.isNumeric(numberOne) || !MathUtil.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return math.multiplication( MathUtil.convertToDouble(numberOne), MathUtil.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) {
		if(!MathUtil.isNumeric(numberOne) || !MathUtil.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}else if(MathUtil.convertToDouble(numberOne) == 0 || MathUtil.convertToDouble(numberTwo) == 0) {
			throw new UnsupportedMathOperationException("It's not possible make division by 0");
		}
		return math.division(MathUtil.convertToDouble(numberOne), MathUtil.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double avg(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
		) {
		if(!MathUtil.isNumeric(numberOne) || !MathUtil.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		
		return math.average(MathUtil.convertToDouble(numberOne), MathUtil.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "sqr/{number}", method = RequestMethod.GET)
	public Double sqr(
			@PathVariable(value = "number") String number
		) {
		if(!MathUtil.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.sqr(MathUtil.convertToDouble(number));
		
	}
	
}
