package android.net;

import android.net.connectivity.android.net.ITetheredInterfaceCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IEthernetManager extends IInterface {
    void requestTetheredInterface(ITetheredInterfaceCallback callback) throws RemoteException;

    abstract class Stub extends Binder implements IEthernetManager {
        public static IEthernetManager asInterface(IBinder binder) {
            throw new UnsupportedOperationException("Stub!");
        }
    }
}
