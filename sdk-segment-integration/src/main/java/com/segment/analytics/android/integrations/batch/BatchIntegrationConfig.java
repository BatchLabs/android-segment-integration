package com.segment.analytics.android.integrations.batch;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class BatchIntegrationConfig
{
    private static final String PREFERENCES_NAME = "batchSegmentPreferences";

    public static final String APIKEY_KEY = "apiKey";
    public static final String CAN_USE_ADVERTISING_ID_KEY = "canUseAdvertisingID";
    public static final String CAN_USE_ADVANCED_DEVICE_INFO_KEY = "canUseAdvancedDeviceInformation";

    public static final boolean DEFAULT_CAN_USE_ADVERTISING_ID = true;
    public static final boolean DEFAULT_CAN_USE_ADV_DEVICE_INFO = true;

    public static boolean enableAutomaticLifecycleManagement = true;

    String apiKey;
    boolean canUseAdvertisingID = DEFAULT_CAN_USE_ADVERTISING_ID;
    boolean canUseAdvancedDeviceInformation = DEFAULT_CAN_USE_ADV_DEVICE_INFO;

    BatchIntegrationConfig(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public static BatchIntegrationConfig loadConfig(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        BatchIntegrationConfig config = new BatchIntegrationConfig(
                preferences.getString(APIKEY_KEY, null)
        );
        config.canUseAdvertisingID = preferences.getBoolean(CAN_USE_ADVERTISING_ID_KEY, config.canUseAdvertisingID);
        config.canUseAdvancedDeviceInformation = preferences.getBoolean(CAN_USE_ADVANCED_DEVICE_INFO_KEY, config.canUseAdvancedDeviceInformation);

        if (config.isValid())
        {
            return config;
        }
        else
        {
            return null;
        }
    }

    /**
     A valid config is a config with a non empty API Key
     */
    public boolean isValid()
    {
        return !TextUtils.isEmpty(apiKey);
    }

    public void save(Context context)
    {
        if (!isValid())
        {
            // Don't bother saving a config with no API Key
            return;
        }

        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
                .putBoolean(CAN_USE_ADVERTISING_ID_KEY, canUseAdvertisingID)
                .putBoolean(CAN_USE_ADVANCED_DEVICE_INFO_KEY, canUseAdvancedDeviceInformation)
                .putString(APIKEY_KEY, apiKey)
                .apply();
    }
}
