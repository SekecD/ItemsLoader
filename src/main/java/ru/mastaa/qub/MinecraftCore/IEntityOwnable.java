package ru.mastaa.qub.MinecraftCore;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.Entity;
import java.util.UUID;

public interface IEntityOwnable
{
    @Nullable
    UUID getOwnerId();

    @Nullable
    Entity getOwner();
}
