package dev.sprock.pixelexplorer.shared.network.packet.login;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginPacket extends Packet
{
    private String username;

    @Override
    public int getPacketId()
    {
        return PacketConstants.LOGIN_PACKET_ID;
    }

    @Override
    public void read(ByteBuf in)
    {
        this.username = this.readString(in);
    }

    @Override
    public void write(ByteBuf out)
    {
        this.writeString(out, this.username);
    }
}
