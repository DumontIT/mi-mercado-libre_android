package com.nbempire.mimercadolibre.component;

import com.facebook.stetho.Stetho;

/**
 * Created by Nahuel Barrios <barrios.nahuel@gmail.com> on 19/07/15.
 */
public class MiMercadoLibreDebugApplication extends MiMercadoLibreApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
