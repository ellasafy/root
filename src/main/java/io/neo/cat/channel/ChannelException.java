package io.neo.cat.channel;

/**
 * Created by echo on 6/12/2016.
 */
public class ChannelException extends RuntimeException {

    private static final long serialVersionUID = 2908618315971075004L;

    /**
     * Creates a new exception.
     */
    public ChannelException() {
    }

    /**
     * Creates a new exception.
     */
    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception.
     */
    public ChannelException(String message) {
        super(message);
    }

    /**
     * Creates a new exception.
     */
    public ChannelException(Throwable cause) {
        super(cause);
    }
}
