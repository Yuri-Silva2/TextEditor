package org.texteditor.models;

import java.util.UUID;

/*
 * A Java record representing a text file with properties such as UUID, name, file path, content, and saved status.
 * Records are a feature introduced in Java 16 for concise and immutable data classes.
 */
public record TextFile(UUID uuid, String name, String filePath, String text, boolean saved) {
}
// The properties of the record are automatically generated based on the provided components.

// Constructor is automatically generated with the provided components.

/*
 * Automatically generated methods:
 * - equals(Object obj): Compares this TextFile with another object for equality.
 * - hashCode(): Generates a hash code for this TextFile.
 * - toString(): Generates a string representation of this TextFile.
 */

