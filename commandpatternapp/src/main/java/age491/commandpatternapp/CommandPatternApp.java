package age491.commandpatternapp;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CommandPatternApp extends Application
{
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	CommandPattern commandPattern = new CommandPattern();
	/**
	 * Enables the mouse to be used as an input to draw on the canvas.
	 */
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>()
	{
		public void handle(MouseEvent event)
		{
			DrawCommand d = new DrawCommand(gc, event.getX(), event.getY());
			commandPattern.addCommand(d);
		}
	};
	/**
	 * This allows keyboard to be used for changing the different aspects of the application.
	 * In this case it only changes the paint color to green.
	 */
	EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>()
	{
		public void handle(KeyEvent event)
		{
			if(event.getCode() == KeyCode.R)
			{
				commandPattern.redo();
			}
			if(event.getCode() == KeyCode.U)
			{
				commandPattern.undo();
			}
		}
	};
	
	public static void main(String[] args) {
		launch(args);

	}
	
	
	public void start(Stage primaryStage) throws Exception 
	{
		primaryStage.setTitle("Command Pattern Drawing App");
		root = new Pane();
		scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		canvas = new Canvas(600,400);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnKeyPressed(keyboardHandler);
	}

}
