Java.perform(function () {

    console.log("[*] SharedPreferences hook loaded");

    // --- Hook access to SharedPreferences files ---
    const ContextImpl = Java.use("android.app.ContextImpl");

    ContextImpl.getSharedPreferences
        .overload("java.lang.String", "int")
        .implementation = function (name, mode) {

        console.log("\n[SP FILE OPENED] name =", name, " mode =", mode);

        const prefs = this.getSharedPreferences(name, mode);

        try {
            const all = prefs.getAll();
            const it = all.entrySet().iterator();

            console.log("[SP DUMP]");
            while (it.hasNext()) {
                const entry = it.next();
                console.log("   ", entry.getKey(), "=", entry.getValue());
            }
        } catch (e) {
            console.log("[!] Failed dumping prefs:", e);
        }

        return prefs;
    };


    // --- Hook READS ---
    const SPImpl = Java.use("android.app.SharedPreferencesImpl");

    SPImpl.getString.overload(
        "java.lang.String",
        "java.lang.String"
    ).implementation = function (key, def) {

        const value = this.getString(key, def);
        console.log("[SP READ] getString:", key, "=", value);

        // Example override:
        // if (key === "is_premium") return "true";

        return value;
    };

    SPImpl.getBoolean.implementation = function (key, def) {
        const value = this.getBoolean(key, def);
        console.log("[SP READ] getBoolean:", key, "=", value);

        // Example override:
        // if (key === "debug") return true;

        return value;
    };

    SPImpl.getInt.implementation = function (key, def) {
        const value = this.getInt(key, def);
        console.log("[SP READ] getInt:", key, "=", value);
        return value;
    };


    // --- Hook WRITES ---
    const EditorImpl = Java.use("android.app.SharedPreferencesImpl$EditorImpl");

    EditorImpl.putString.implementation = function (key, value) {
        console.log("[SP WRITE] putString:", key, "=", value);

        // Example modification:
        // if (key === "user_role") value = "admin";

        return this.putString(key, value);
    };

    EditorImpl.putBoolean.implementation = function (key, value) {
        console.log("[SP WRITE] putBoolean:", key, "=", value);
        return this.putBoolean(key, value);
    };

    EditorImpl.putInt.implementation = function (key, value) {
        console.log("[SP WRITE] putInt:", key, value);
        return this.putInt(key, value);
    };

    EditorImpl.commit.implementation = function () {
        console.log("[SP WRITE] commit()");
        return this.commit();
    };

    EditorImpl.apply.implementation = function () {
        console.log("[SP WRITE] apply()");
        return this.apply();
    };

});
