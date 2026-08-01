package android.net.connectivity.android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface ITetheredInterfaceCallback extends IInterface {
    void onAvailable(String iface) throws RemoteException;
    void onUnavailable() throws RemoteException;

    abstract class Stub extends Binder implements ITetheredInterfaceCallback {
        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
