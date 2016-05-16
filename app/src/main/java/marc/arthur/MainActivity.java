package marc.arthur;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import marc.arthur.DeviceConnections.DeviceConnectionFactory;
import marc.arthur.DeviceConnections.IpConnection;
import marc.arthur.PacketBuilders.ArthurCommandByteBuilder;
import marc.arthur.PacketBuilders.ArthurEnvelopeBuilder;
import marc.arthur.PacketBuilders.ArthurResponseByteBuilder;
import marc.arthur.PacketBuilders.PacketBuilderFactory;

public class MainActivity extends AppCompatActivity {

    Button buttonDoPentagon;
    EditText editTextIp;
    Button buttonClose;
    EditText editTextSpeech;
    Button buttonSpeak;

    ArthurController.CallBack callBack;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        buttonDoPentagon = (Button) findViewById(R.id.buttonDoPentagon);
        editTextIp = (EditText) findViewById(R.id.editTextIp);
        buttonClose = (Button) findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editTextSpeech = (EditText) findViewById(R.id.editTextSpeech);
        buttonSpeak = (Button) findViewById(R.id.buttonSpeak);

        // BUILD ROBOT CONTROLLER
        DeviceConnectionFactory deviceConnectionFactory = new DeviceConnectionFactory();
        PacketBuilderFactory packetBuilderFactory = new PacketBuilderFactory();

        ArthurController.Builder builder = new ArthurController.Builder();

        //builder.setDeviceConnection(
        //        deviceConnectionFactory.createDeviceConnection(DeviceConnectionFactory.DeviceConnectionType.DUMMY) );
        IpConnection ipConnection = (IpConnection) deviceConnectionFactory.createDeviceConnection(DeviceConnectionFactory.DeviceConnectionType.IP);
        ipConnection.setIp( editTextIp.getText().toString() );
        ipConnection.setPort(12345);

        builder.setDeviceConnection( ipConnection );

        builder.setArthurEnvelopeBuilder(
                (ArthurEnvelopeBuilder) packetBuilderFactory.createPacketBuilder(PacketBuilderFactory.PacketBuilderType.ARTHUR_ENVELOPE));
        builder.setArthurCommandByteBuilder(
                (ArthurCommandByteBuilder) packetBuilderFactory.createPacketBuilder(PacketBuilderFactory.PacketBuilderType.ARTHUR_COMMAND_BYTE_BUILDER));
        builder.setArthurResponseByteBuilder(
                (ArthurResponseByteBuilder) packetBuilderFactory.createPacketBuilder(PacketBuilderFactory.PacketBuilderType.ARTHUR_RESPONSE_BYTE_BUILDER));

        final ArthurController arthurController = builder.build();


        // PENTAGON BUTTON
        buttonDoPentagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(){

                    @Override
                    public void run() {
                        super.run();

                        //arthurController.preformStraightLine();

                        arthurController.getInPlaceForPentagone(new ArthurController.CallBack() {
                            @Override
                            public void onProgramCompleted(Boolean success) {


                            arthurController.performPentagon();

                            }
                        });


                    }
                }.start();

            }
        });


        // SPEECH EDIT TEXT
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){

                    @Override
                    public void run() {
                        super.run();

                        String speech = editTextSpeech.getText().toString();
                        arthurController.speak( speech , new ArthurController.CallBack() {
                            @Override
                            public void onProgramCompleted(Boolean success) {

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        editTextSpeech.setText("");
                                    }
                                });
                            }
                        });
                    }

                }.start();

            }
        });


    }



}
