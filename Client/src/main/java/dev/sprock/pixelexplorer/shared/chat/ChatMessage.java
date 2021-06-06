package dev.sprock.pixelexplorer.shared.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessage
{
    private String message;
    private long timestamp;
}
