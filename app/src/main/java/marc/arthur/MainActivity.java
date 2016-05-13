package marc.arthur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import marc.arthur.DeviceConnections.DeviceConnectionFactory;
import marc.arthur.PacketBuilders.ArthurCommandByteBuilder;
import marc.arthur.PacketBuilders.ArthurEnvelopeBuilder;
import marc.arthur.PacketBuilders.ArthurResponseByteBuilder;
import marc.arthur.PacketBuilders.PacketBuilderFactory;

public class MainActivity extends AppCompatActivity {

    Button buttonDoPentagon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // BUILD ROBOT CONTROLLER
        DeviceConnectionFactory deviceConnectionFactory = new DeviceConnectionFactory();
        PacketBuilderFactory packetBuilderFactory = new PacketBuilderFactory();

        ArthurController.Builder builder = new ArthurController.Builder();
        builder.setDeviceConnection(
                deviceConnectionFactory.createDeviceConnection(DeviceConnectionFactory.DeviceConnectionType.DUMMY) );
        builder.setArthurEnvelopeBuilder(
                (ArthurEnvelopeBuilder) packetBuilderFactory.createPacketBuilder(PacketBuilderFactory.PacketBuilderType.ARTHUR_ENVELOPE));
        builder.setArthurCommandByteBuilder(
                (ArthurCommandByteBuilder) packetBuilderFactory.createPacketBuilder(PacketBuilderFactory.PacketBuilderType.ARTHUR_COMMAND_BYTE_BUILDER));
        builder.setArthurResponseByteBuilder(
                (ArthurResponseByteBuilder) packetBuilderFactory.createPacketBuilder(PacketBuilderFactory.PacketBuilderType.ARTHUR_RESPONSE_BYTE_BUILDER));

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
