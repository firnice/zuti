package com.firnice.zuti.multithread.pk2;


import java.security.*;

public abstract class SafeRunnable implements Runnable {

    public abstract void protectedRun(
    );

    @Override
    public final void run() {
        CodeSource nullSource = new CodeSource(null, (CodeSigner[]) null);
        PermissionCollection noPerms = new Permissions();
        ProtectionDomain domain = new ProtectionDomain(nullSource, noPerms);
        AccessControlContext safeContext = new AccessControlContext(new ProtectionDomain[] { domain });

        AccessController.doPrivileged((PrivilegedAction) () -> {
            protectedRun();
            return null;
        }, safeContext);
    }
}