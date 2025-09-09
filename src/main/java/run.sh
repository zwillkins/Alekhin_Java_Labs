SOURCE_ROOT="$HOME/Alekhin_Java_Labs/src/main/java"


if [ "$1" == "-d" ]; then
    DELETE_ONLY=true
    PACKAGE_NAME="$2"
    if [ -z "$PACKAGE_NAME" ]; then
        echo "Ошибка: не указана директория."
        exit 1
    fi
else
    DELETE_ONLY=false
    PACKAGE_NAME="$1"
fi

cd "$SOURCE_ROOT" || { echo "Ошибка: не найдена директория: $SOURCE_ROOT"; exit 1; }

find "ru/$PACKAGE_NAME" -name "*.class" -delete

if [ "$DELETE_ONLY" == true ]; then
    exit 0
fi

javac "ru/$PACKAGE_NAME/Main.java"

if [ $? -ne 0 ]; then
    echo "Ошибка компиляции."
    exit 1
fi

java "ru.$PACKAGE_NAME.Main"