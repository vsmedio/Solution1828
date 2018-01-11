/* 
Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD

Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id

Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u - обновляет данные товара с заданным id
-d - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234


Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-u id productName price quantity" должна обновится информация о товаре в файле.
4. При запуске программы с параметрами "-d id" строка товара с заданным id должна быть удалена из файла.
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> buffer = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String line = null;
        if (args.length  > 0) {

            // read all file in buffer
            BufferedReader fin = new BufferedReader(new FileReader(fileName));
            while (fin.ready())
                buffer.add(fin.readLine());
            fin.close();

            // write all string to the file
            BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
            for (String str : buffer)
                if (args[0].equals("-u") && str.startsWith(args[1]))      // update
                    fout.write(String.format("%-8s%-30s%-8s%-4s\n",
                            args[1], args[2], args[3], args[4]));
                else if (args[0].equals("-d") && str.startsWith(args[1])) // delete
                    continue;
                else
                    fout.write(str + "\n");

            fout.close();
        }
    }
}