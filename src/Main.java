import view.TitleScreen;

// MAIN METHOD
public class Main {
    /* CALL THIS TO BEGIN THE FUN

                        .s$$$Ss.
            .8,         $$$. _. .              ..sS$$$$$"  ...,.;
 o.   ,@..  88        =.$"$'  '          ..sS$$$$$$$$$$$$s. _;"'
  @@@.@@@. .88.   `  ` ""l. .sS$$.._.sS$$$$$$$$$$$$S'"'
   .@@@q@@.8888o.         .s$$$$$$$$$$$$$$$$$$$$$'
     .:`@@@@33333.       .>$$$$$$$$$$$$$$$$$$$$'
     .: `@@@@333'       ..>$$$$$$$$$$$$$$$$$$$'
      :  `@@333.     `.,   s$$$$$$$$$$$$$$$$$'
      :   `@33       $$ S.s$$$$$$$$$$$$$$$$$'
      .S   `Y      ..`  ,"$' `$$$$$$$$$$$$$$
      $s  .       ..S$s,    . .`$$$$$$$$$$$$.
      $s .,      ,s ,$$$$,,sS$s.$$$$$$$$$$$$$.
      / /$$SsS.s. ..s$$$$$$$$$$$$$$$$$$$$$$$$$.
     /`.`$$$$$dN.ssS$$'`$$$$$$$$$$$$$$$$$$$$$$$.
    ///   `$$$$$$$$$'    `$$$$$$$$$$$$$$$$$$$$$$.
   ///|     `S$$S$'       `$$$$$$$$$$$$$$$$$$$$$$.
  / /                      $$$$$$$$$$$$$$$$$$$$$.
                           `$$$$$$$$$$$$$$$$$$$$$s.
                            $$$"'        .?T$$$$$$$
                           .$'        ...      ?$$#\
                           !       -=S$$$$$s
                         .!       -=s$$'  `$=-_      :
                        ,        .$$$'     `$,       .|
                       ,       .$$$'          .        ,
                      ,     ..$$$'
                          .s$$$'                 `s     .
                   .   .s$$$$'                    $s. ..$s
                  .  .s$$$$'                      `$s=s$$$
                    .$$$$'                         ,    $$s
               `   " .$$'                               $$$
               ,   s$$'                              .  $$$s
            ` .s..s$'                                .s ,$$
             .s$$$'                                   "s$$$,
          -   $$$'                                     .$$$$.
        ."  .s$$s                                     .$',',$.
        $s.s$$$$S..............   ................    $$....s$s......
         `""'           `     ```"""""""""""""""         `""   ``
     */
	// ARGS ARE BOOLEAN: IS HOST OF THE GAME, STRING: IPADDRESS TO CONNECT TO
    public static void main(String[] args) {
        new TitleScreen(false, "130.195.7.130");
    }

}
