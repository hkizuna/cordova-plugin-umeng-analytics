package xwang.cordova.umeng.analytics;

import android.util.Log;
import com.umeng.analytics.MobclickAgent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UmengAnalytics extends CordovaPlugin {
    public static final String TAG = "xwang.cordova.analytics";
    public static final String ERROR_INVALID_PARAMETERS = "参数错误";

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        MobclickAgent.openActivityDurationTrack(false);
        Log.d(TAG, "umeng analytics plugin initialzed.");
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        MobclickAgent.onPause(webView.getContext());
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        MobclickAgent.onResume(webView.getContext());
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("profileSignInWithPUID")) {
            return profileSignInWithPUID(args, callbackContext);
        }
        else if (action.equals("profileSignOff")) {
            return profileSignOff(args, callbackContext);
        }
        else if (action.equals("logPageViewWithDuration")) {
            return logPageViewWithDuration(args, callbackContext);
        }
        else if (action.equals("beginLogPageView")) {
            return beginLogPageView(args, callbackContext);
        }
        else if (action.equals("endLogPageView")) {
            return endLogPageView(args, callbackContext);
        }
        else if (action.equals("event")) {
            return event(args, callbackContext);
        }
        else if (action.equals("eventWithLabel")) {
            return eventWithLabel(args, callbackContext);
        }
        else if (action.equals("eventWithAttributes")) {
            return eventWithAttributes(args, callbackContext);
        }
        else if (action.equals("eventWithAttributesAndCounter")) {
            return eventWithAttributesAndCounter(args, callbackContext);
        }
        else if (action.equals("beginEvent")) {
            return beginEvent(args, callbackContext);
        }
        else if (action.equals("endEvent")) {
            return endEvent(args, callbackContext);
        }
        else if (action.equals("beginEventWithLabel")) {
            return beginEventWithLabel(args, callbackContext);
        }
        else if (action.equals("endEventWithLabel")) {
            return endEventWithLabel(args, callbackContext);
        }
        else if (action.equals("beginEventWithKeyAndAttributes")) {
            return beginEventWithKeyAndAttributes(args, callbackContext);
        }
        else if (action.equals("endEventWithKey")) {
            return endEventWithKey(args, callbackContext);
        }
        else if (action.equals("eventWithDuration")) {
            return eventWithDuration(args, callbackContext);
        }
        else if (action.equals("eventWithLabelAndDuration")) {
            return eventWithLabelAndDuration(args, callbackContext);
        }
        else if (action.equals("eventWithAttributesAndDuration")) {
            return eventWithAttributesAndDuration(args, callbackContext);
        }

        return false;
    }

    protected boolean profileSignInWithPUID(CordovaArgs args, final CallbackContext callbackContext) {
        final String puid, provider;
        try {
            puid = args.getString(0);
            provider = args.getString(1);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onProfileSignIn(puid, provider);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean profileSignOff(CordovaArgs args, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onProfileSignOff();
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean logPageViewWithDuration(CordovaArgs args, final CallbackContext callbackContext) {
        callbackContext.error("FUCK ANDROID");
        return true;
    }

    protected boolean beginLogPageView(CordovaArgs args, final CallbackContext callbackContext) {
        final String pageName;
        try {
            pageName = args.getString(0);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onPageStart(pageName);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean endLogPageView(CordovaArgs args, final CallbackContext callbackContext) {
        final String pageName;
        try {
            pageName = args.getString(0);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onPageEnd(pageName);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean event(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        try {
            eventId = args.getString(0);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEvent(webView.getContext(), eventId);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean eventWithLabel(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId, label;
        try {
            eventId = args.getString(0);
            label = args.getString(1);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEvent(webView.getContext(), eventId, label);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean eventWithAttributes(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        final Map<String, String> attributes;
        try {
            eventId = args.getString(0);
            attributes = toMap(args.getJSONObject(1));
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEvent(webView.getContext(), eventId, attributes);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean eventWithAttributesAndCounter(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        final Map<String, String> attributes;
        final int counter;
        try {
            eventId = args.getString(0);
            attributes = toMap(args.getJSONObject(1));
            counter = args.getInt(2);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventValue(webView.getContext(), eventId, attributes, counter);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean beginEvent(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        try {
            eventId = args.getString(0);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventBegin(webView.getContext(), eventId);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean endEvent(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        try {
            eventId = args.getString(0);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventEnd(webView.getContext(), eventId);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean beginEventWithLabel(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId, label;
        try {
            eventId = args.getString(0);
            label = args.getString(1);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventBegin(webView.getContext(), eventId, label);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean endEventWithLabel(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId, label;
        try {
            eventId = args.getString(0);
            label = args.getString(1);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventEnd(webView.getContext(), eventId, label);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean beginEventWithKeyAndAttributes(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId, primaryKey;
        final Map<String, String> attributes;
        try {
            eventId = args.getString(0);
            primaryKey = args.getString(1);
            attributes = toMap(args.getJSONObject(2));
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onKVEventBegin(webView.getContext(), eventId, attributes, primaryKey);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean endEventWithKey(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId, primaryKey;
        try {
            eventId = args.getString(0);
            primaryKey = args.getString(1);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onKVEventEnd(webView.getContext(), eventId, primaryKey);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean eventWithDuration(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        final long durations;
        try {
            eventId = args.getString(0);
            durations = args.getLong(1);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventDuration(webView.getContext(), eventId, durations);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean eventWithLabelAndDuration(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId, label;
        final long durations;
        try {
            eventId = args.getString(0);
            label = args.getString(1);
            durations = args.getLong(2);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventDuration(webView.getContext(), eventId, label, durations);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    protected boolean eventWithAttributesAndDuration(CordovaArgs args, final CallbackContext callbackContext) {
        final String eventId;
        final Map<String, String> attributes;
        final long durations;
        try {
            eventId = args.getString(0);
            attributes = toMap(args.getJSONObject(1));
            durations = args.getLong(2);
        } catch (JSONException e) {
            callbackContext.error(ERROR_INVALID_PARAMETERS);
            return true;
        }

        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                MobclickAgent.onEventDuration(webView.getContext(), eventId, attributes, durations);
                callbackContext.success("SUCCESS");
            }
        });

        sendNoResultPluginResult(callbackContext);
        return true;
    }

    private void sendNoResultPluginResult(CallbackContext callbackContext) {
        PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
        result.setKeepCallback(true);
        callbackContext.sendPluginResult(result);
    }

    private static Map<String, String> toMap(JSONObject json) throws JSONException {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> keysIt = json.keys();
        while(keysIt.hasNext()) {
            String key = keysIt.next();
            String value = (String) json.get(key);
            map.put(key, value);
        }
        return map;
    }
}