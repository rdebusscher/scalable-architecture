package fish.payara.microservices.integrated.packaging.exception;

import fish.payara.microservices.integrated.common.exception.BusinessException;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            // get the exception from context
            Throwable t = context.getException();

            Throwable businessException = getBusinessException(t);

            //here you do what ever you want with exception
            if (businessException != null) {
                try {

                    FacesContext instance = FacesContext.getCurrentInstance();

                    String msg = businessException.getMessage();

                    instance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));

                } finally {
                    //remove it from queue
                    i.remove();
                }
            }
        }
        //parent handle
        getWrapped().handle();
    }

    private Throwable getBusinessException(Throwable exception) {
        Throwable result = null;
        while (result == null && exception != null) {
            if (!BusinessException.class.isAssignableFrom(exception.getClass())) {
                exception = exception.getCause();
            } else {
                result = exception;
            }
        }
        return result;
    }
}