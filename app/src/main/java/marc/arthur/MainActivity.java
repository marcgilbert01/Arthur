package marc.arthur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import marc.arthur.DeviceConnections.DeviceConnectionFactory;
import marc.arthur.EnvelopeBuilders.EnvelopeBuilderFactory;

public class MainActivity extends AppCompatActivity {

    Button buttonDoPentagon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // BUILD ROBOT CONTROLLER
        EnvelopeBuilderFactory envelopeBuilderFactory = new EnvelopeBuilderFactory();
        DeviceConnectionFactory deviceConnectionFactory = new DeviceConnectionFactory();

        ArthurController.Builder builder = new ArthurController.Builder();
        builder.setEnvelopeBuilder(
                envelopeBuilderFactory.createEnvelopeBuilder(EnvelopeBuilderFactory.EnvelopBuilderType.ARTHUR) );
        builder.setDeviceConnection(
                deviceConnectionFactory.createDeviceConnection(DeviceConnectionFactory.DeviceConnectionType.DUMMY) );
        final ArthurController arthurController = builder.build();


        // INIT BUTTON
        buttonDoPentagon = (Button) findViewById(R.id.buttonDoPentagon);

        buttonDoPentagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arthurController.preformStraightLine();

            }
        });



    }











}
