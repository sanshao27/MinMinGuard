package tw.fatminmin.xposed.minminguard.blocker.adnetwork;

import static de.robv.android.xposed.XposedHelpers.findClass;
import tw.fatminmin.xposed.minminguard.Main;
import tw.fatminmin.xposed.minminguard.blocker.ApiBlocking;
import tw.fatminmin.xposed.minminguard.blocker.Blocker;
import tw.fatminmin.xposed.minminguard.blocker.Util;
import android.view.View;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Admob extends Blocker {
    
    public static final String BANNER = "com.google.ads.AdView";
    public static final String BANNER_PREFIX = "com.google.ads";

	public static final String INTER_ADS = "com.google.ads.InterstitialAd";

	public boolean handleLoadPackage(final String packageName, LoadPackageParam lpparam, final boolean removeAd) {
		try {

			ApiBlocking.removeBanner(packageName, BANNER, "loadAd", lpparam, removeAd);
			ApiBlocking.blockAdFunction(packageName, INTER_ADS, "loadAd", lpparam, removeAd);
			ApiBlocking.blockAdFunction(packageName, INTER_ADS, "show", lpparam, removeAd);

			
			Util.log(packageName, packageName + " uses Admob");
		}
		catch(ClassNotFoundError e) {
			return false;
		}
		return true;
	}
	@Override
	public String getBannerPrefix() {
		return BANNER_PREFIX;
	}

	@Override
	public String getBanner() {
		return BANNER;
	}
}
