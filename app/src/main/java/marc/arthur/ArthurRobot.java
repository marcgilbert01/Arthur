package marc.arthur;

import marc.arthur.Actions.ArthurCommandExecutor;
import marc.arthur.Actions.CommandExecutorFactory;
import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.DeviceConnections.DeviceConnectionFactory;
import marc.arthur.EnvelopeBuilders.EnvelopeBuilder;
import marc.arthur.EnvelopeBuilders.EnvelopeBuilderFactory;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class ArthurRobot {

    EnvelopeBuilder envelopeBuilder;
    DeviceConnection deviceConnection;

    ArthurCommandExecutor moveCommand;


    public void init(){

        envelopeBuilder  = new EnvelopeBuilderFactory().createEnvelopeBuilder("Arthur");
        deviceConnection = new DeviceConnectionFactory().createDeviceConnection("BLUETOOTH");
        moveCommand = new CommandExecutorFactory().createArthurCommandExecutor("MOVE");
        moveCommand.setEnvelopeBuilder(envelopeBuilder);
        moveCommand.setDeviceConnection(deviceConnection);
    }




    public void performStraightLine( int meters ){


    }


    public void performPentagon(){


    }




}
