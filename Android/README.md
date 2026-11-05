# Android build

The Android app is generated in GitHub Actions from the existing static PWA in `../WebApp`.

## Build and download

1. Open the repository's **Actions** tab.
2. Select **Build Android APK**.
3. Choose **Run workflow** on the `main` branch.
4. Wait for the workflow to finish successfully.
5. Open the completed run and download the `fancy-steel-invoice-android-debug` artifact.
6. Extract the ZIP and install `app-debug.apk` on your Android phone. Android may ask you to allow installation from the browser or file manager.

The workflow also runs automatically when files under `WebApp/` change on `main`.

## Notes

- This is a debug APK for testing, not a Play Store release.
- The app bundles the current `WebApp/` files locally inside a Capacitor Android WebView; it does not depend on the Render website being open.
- Browser localStorage is isolated per app. Existing bills and customer data in Chrome/your web PWA will **not** automatically appear in the Android app. Use the app's backup/export and restore controls to transfer data.
- No Android signing secret is required for this debug build. A release build would need a private signing key and secure GitHub Actions secrets.
