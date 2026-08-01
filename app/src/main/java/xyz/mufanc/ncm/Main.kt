package xyz.mufanc.ncm

import android.net.IEthernetManager
import android.net.connectivity.android.net.ITetheredInterfaceCallback
import android.os.Binder
import android.os.ProcessHidden
import android.os.ServiceManager
import android.util.Log
import xyz.mufanc.aproc.annotation.AProcEntry
import kotlin.system.exitProcess

private const val TAG = "NcmTetheringFix"
private const val PROCESS_NAME = "ncm-tethering"

@AProcEntry
object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            run()
        } catch (error: Throwable) {
            Log.e(TAG, "helper failed", error)
        }
    }

    private fun run() {
        ProcessHidden.setArgV0(PROCESS_NAME)

        val service = ServiceManager.getService("ethernet") ?: run {
            Log.e(TAG, "ethernet service is unavailable")
            return
        }

        val ethernet = IEthernetManager.Stub.asInterface(service) ?: run {
            Log.e(TAG, "ethernet service has no interface")
            return
        }

        val callback = Callback()

        service.linkToDeath({
            Log.e(TAG, "ethernet service died")
            exitProcess(1)
        }, 0)
        ethernet.requestTetheredInterface(callback)

        Log.i(TAG, "holding Ethernet tethering interface")
        Binder.joinThreadPool()
        Log.e(TAG, "Binder thread pool exited")
    }
}

private class Callback : ITetheredInterfaceCallback.Stub() {
    override fun onAvailable(iface: String) {
        Log.i(TAG, "available: $iface")
    }

    override fun onUnavailable() {
        Log.w(TAG, "unavailable")
    }
}
