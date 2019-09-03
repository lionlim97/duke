import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private static String[] arr;
    public static Command parse(String fullCommand) throws DukeException {
        try {
            if (fullCommand.trim().equals("bye")) {
                return new ByeCommand();
            }

            else if (fullCommand.trim().equals("list")) {
                return new ListCommand();
            }

            else if (fullCommand.trim().substring(0, 4).equals("done")) {
                try {
                    arr = fullCommand.split(" ");
                    int index = Integer.parseInt(arr[1]) - 1;
                    return new DoneCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("     \u2639" + " OOPS!!! Please enter a valid task number.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("     \u2639" + " OOPS!!! Please do not leave task number blank.");
                }
            }

            else if (fullCommand.trim().substring(0, 4).equals("find")) {
                try {
                    String key = fullCommand.trim().substring(5);
                    if (key.trim().isEmpty()) {
                        throw new DukeException("     \u2639" + " OOPS!!! Please do not leave the keyword blank.");
                    } else {
                        return new FindCommand(key);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("     \u2639" + " OOPS!!! Please enter keyword.");
                }
            }

            else if (fullCommand.trim().substring(0, 4).equals("todo")) {
                String activity = fullCommand.trim().substring(4).trim();
                if (activity.isEmpty()) {
                    throw new DukeException("     \u2639" + " OOPS!!! The description of a todo cannot be empty.");
                } else {
                    return new AddCommand(new Todo(activity));
                }
            }

            else if (fullCommand.trim().substring(0, 5).equals("event")) {
                try {
                    String activity = fullCommand.trim().substring(5);
                    arr = activity.split("/at");
                    if (arr[0].trim().isEmpty()) {
                        throw new DukeException("     \u2639" + " OOPS!!! The description of a event cannot be empty.");
                    } else {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Date date = formatter.parse(arr[1].trim());
                        return new AddCommand(new Event(arr[0].trim(), date));
                    }
                } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("     OOPS!!! Please enter event as follows:\n" +
                            "     event name_of_event /at dd/MM/yyyy HHmm\n" +
                            "     For example: event project meeting /at 1/1/2020 1500");
                }
            }

            else if (fullCommand.trim().substring(0, 6).equals("delete")) {
                try {
                    arr = fullCommand.split(" ");
                    int index = Integer.parseInt(arr[1]) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("     \u2639" + " OOPS!!! Please enter a valid task number.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("     \u2639" + " OOPS!!! Please do not leave task number blank.");
                }
            }

            else if (fullCommand.trim().substring(0, 8).equals("deadline")) {
                try {
                    String activity = fullCommand.trim().substring(8);
                    arr = activity.split("/by");
                    if (arr[0].trim().isEmpty()) {
                        throw new DukeException("     \u2639" + " OOPS!!! The description of a deadline cannot be empty.");
                    }
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    Date date = formatter.parse(arr[1].trim());
                    return new AddCommand(new Deadline(arr[0].trim(), date));
                } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("     OOPS!!! Please enter deadline as follows:\n" +
                            "     deadline name_of_activity /by dd/MM/yyyy HHmm\n" +
                            "     For example: deadline return book /by 2/12/2019 1800");
                }
            }

            else {
                throw new DukeException("     \u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (StringIndexOutOfBoundsException e){
            throw new DukeException("     \u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
