      $( document ).ready( function() {
        var jbOffset = $( '#menu_bar' ).offset();
        $( window ).scroll( function() {
          if ( $( document ).scrollTop() > jbOffset.top ) {
            $( '#menu_inner' ).addClass( 'jbFixed' );
          }
          else {
            $( '#menu_inner' ).removeClass( 'jbFixed' );
          }
        });
      } );