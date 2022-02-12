package password.authentication.qualified;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStream inputStream = new FileInputStream(new File("./src/main/java/password/authentication/qualified/input.txt"));
		//		InputStream inputStream = System.in;
		BufferedReader reader = new BufferedReader(new InputStreamReader((inputStream)));
		String s;
		String output = "";
		while((s = reader.readLine())!=null){
			System.out.println("==> " + s);
			int len = s.length();
			char[] arr = s.toCharArray();
			byte low = 0;
			byte up = 0;
			byte num = 0;
			byte others = 0;
			// 1.长度超过8位
			if(len <= 8){
				output += "NG\n";
				continue;
			}
			// 2.包括大小写字母.数字.其它符号,以上四种至少三种
			for(int i = 0; i < len; i++){
				char c = arr[i];
				if(c >= 'a' && c <= 'z'){
					low = 1;
				}else if(c >= 'A' && c <= 'Z'){
					up = 1;
				}else if(c >= '0' && c <= '9'){
					num = 1;
				}else{
					others = 1;
				}
			}
			if(low + up + num + others < 3){
				output += "NG\n";
				continue;
			}
			// 3.不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
			boolean isReapet = false;
			for(int i = 0; i < len - 5; i++){
				if(isReapet) {
					break;
				}
				for(int j = i + 3; j < len - 2; j++){
					System.out.println("" + arr[i] + arr[i+1] + arr[i+2] + "  " + arr[j] + arr[j+1] + arr[j+2]);
					if(arr[i] == arr[j] &&
						arr[i+1] == arr[j+1] &&
							arr[i+2] == arr[j+2]){
						isReapet = true;
						break;
					}
				}
			}
			if(isReapet) {
				output += "NG\n";
				continue;
			}
			output += "OK\n";
		}
		System.out.println(output);
	}
}
