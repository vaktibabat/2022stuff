echo "Usage: $0 <apktool dir> <keystore password>"

apktool b $1 -o $1-crackme.apk

keytool -genkey -alias crackme -keyalg RSA -keystore keystore.jks -keysize 4096
jarsigner  -keystore ./keystore.jks -storepass $2 $1-crackme.apk crackme
zipalign 4 $1-crackme.apk $1-crackme-aligned.apk
adb install $1-crackme-aligned.apk
