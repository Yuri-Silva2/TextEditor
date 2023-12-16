package org.texteditor.model;

import java.util.UUID;

public record TextFile(UUID uuid, String name, String filePath, String text, boolean saved) {
}
