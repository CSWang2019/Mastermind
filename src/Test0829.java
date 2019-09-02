public class Test0829 {
    public static void main(String[] args) {
        String str = "abcadac";
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (i == j) {
                } else {
                    if (str.charAt(i) == str.charAt(j) && Math.abs(i - j) > max) {
                        max = Math.abs(i - j);
                        break;
                    }

                }
            }
        }
        System.out.println(max);
    }
}
