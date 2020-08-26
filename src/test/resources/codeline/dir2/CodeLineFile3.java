/**
 * commented line commented line
 */
public enum TestClass3 {    /*****
 * commented line
 *  \/* commented line
 //*****/
    /***/// Slightly pathological comment ending...
    public static final void main(String[] args) { // comment
        // commented line
        /*comment*/
        System.out./*comment*/println/*comment*/("Hello");/**
         commented line
         */
        System.out.println("/*");
        System.out.println("test");
        System./*comment*/out.println("*/");
    }

}