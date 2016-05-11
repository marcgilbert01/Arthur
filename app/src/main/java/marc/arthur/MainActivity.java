package marc.arthur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonDoPentagon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonDoPentagon = (Button) findViewById(R.id.buttonDoPentagon);

        buttonDoPentagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performPentagon();
            }
        });



    }



    private void performPentagon(){






    }







}
