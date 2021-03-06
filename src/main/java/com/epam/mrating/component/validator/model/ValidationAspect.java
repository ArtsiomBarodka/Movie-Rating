package com.epam.mrating.component.validator.model;

import com.epam.mrating.component.exception.ValidatorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * The type Validation aspect.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@Aspect
public class ValidationAspect {

    @Pointcut("execution(public * com.epam.mrating.model.form.*.set*(String))")
    private void formFieldsValidationPointcut(){}

    /**
     * Validate field object.
     *
     * @param joinPoint the join point
     * @return the object
     */
    @Around("formFieldsValidationPointcut()")
    public Object validateField(ProceedingJoinPoint joinPoint){
        try {
            Object o = joinPoint.getThis();
            String methodName = joinPoint.getStaticPart().getSignature().getName();
            String fieldName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
            String fieldValue = (String) joinPoint.getArgs()[0];
            boolean validate = Validator.validate(o, fieldName, fieldValue);
            if(validate){
                return joinPoint.proceed();
            }
        } catch (NoSuchFieldException e) {
            throw new ValidatorException("Can`t find element", e);
        } catch (IllegalAccessException e) {
            throw new ValidatorException("Illegal access", e);
        } catch (Throwable throwable) {
            throw new ValidatorException("Validation process has failed");
        }
        return null;
    }
}
