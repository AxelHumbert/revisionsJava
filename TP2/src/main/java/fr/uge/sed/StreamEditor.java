package fr.uge.sed;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.Objects;

public class StreamEditor {

  private final LineDeleteCommand command;

  public StreamEditor() {
    command = new LineDeleteCommand(0);
  }

  public StreamEditor(LineDeleteCommand command) {
    Objects.requireNonNull(command);
    this.command = command;
  }

  public record LineDeleteCommand(int lineToDelete) {
    public LineDeleteCommand {
      if (lineToDelete < 0) {
        throw new IllegalArgumentException();
      }
    }
  }

  public void transform(LineNumberReader lineNumberReader, Writer writer) throws IOException {
    Objects.requireNonNull(lineNumberReader);
    Objects.requireNonNull(writer);

    var line = lineNumberReader.readLine();
    while (line != null) {
      if (lineNumberReader.getLineNumber() != command.lineToDelete()) {
        writer.append(line).append('\n');
      }
      line = lineNumberReader.readLine();
    }
  }

  public static LineDeleteCommand lineDelete(int lineToDelete) {
    if (lineToDelete < 0) {
      throw new IllegalArgumentException();
    }
    return new LineDeleteCommand(lineToDelete);
  }
}
