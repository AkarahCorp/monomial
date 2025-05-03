package dev.akarah.monomial.api.event;

public final class CancelToken {
    boolean cancelled;

    public static CancelToken notCancelled() {
        return new CancelToken().uncancel();
    }

    public static CancelToken cancelled() {
        return new CancelToken().cancel();
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public CancelToken cancel() {
        this.cancelled = true;
        return this;
    }

    public CancelToken uncancel() {
        this.cancelled = false;
        return this;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(cancelled);
    }

    @Override
    public String toString() {
        return "CancellationToken[" + this.cancelled + "]";
    }
}
