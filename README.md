# GameEngine

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]

Game engine is a tools available for game designers to code out a game quickly and easily without building one from the ground up. This game engine is a 2D based using JavaFX.

![Sample](samle.gif)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need Java, JavaFX.

You will need to link your game project to the game engine.

### Installing

A step by step examples to help you initialize the game engine for your project.

The first step is to extends from GameApp.

```
public class YourGame extends GameApp {

}
```

The second step is to override several methode :

- The initSetting where you need to define the window characteristic.
- The initLevel
- The initGameObject
- The initInput
- The initCamera
- The initParticle

```
import gameengine.app.GameApp;
import gameengine.app.GameSetting;
import gameengine.entities.GameObject;
import gameengine.entities.builder.BuilderGameObject;
import gameengine.entities.builder.BuilderGameObjectFactory;
import gameengine.entities.builder.WrapperGameObject;
import gameengine.entities.texture.Texture;
import gameengine.input.UserEvent;
import gameengine.particule.Emitter;
import gameengine.particule.FireEmitter;
import gameengine.physic.Point2D;
import gameengine.render.Camera;
import gameengine.world.Level;
import gameengine.world.builder.BuilderLevel;
import javafx.geometry.Dimension2D;
import javafx.scene.input.KeyCode;

public class YourGame extends GameApp {

  @Override
	public void initSetting(GameSetting setting) {
		setting.setWidth(720);
		setting.setHeight(480);
		setting.setTitle("MyGame");
	}
  @Override
  public void initLevel() {
    Level l = BuilderLevel.buildLevel("NameOfTheLevel", "Path", Pas);

  @Override
  public void initGameObject() {

    aGameObject = BuilderGameObject.createGameObject()
        .with(new Dimension2D(32, 32))
        .with(new Texture("FileName"))
        .is("TypeOfTheObject")
        .with(ZIndex);

  	BuilderLevel.addWrapperGameObject(new WrapperGameObject("0",new BuilderGameObjectFactory() {
  		@Override
  		public GameObject createGameObject() {
    			return BuilderGameObject.createGameObject()
  					.with(new Dimension2D(32, 32))
  					.with(new Texture("FileName"))
  					.is("TypeOfTheObject")
					  .with(ZIndex);
		  }
	  }));
		BuilderLevel.addWrapperGameObject(new WrapperGameObject("3",new BuilderGameObjectFactory() {
			@Override
			public GameObject createGameObject() {
  				return aGameObject;
			}
		}));
	}

  @Override
  public void initInput() {
  	getInput().addEvent(new UserEvent("ZoomOut") {
  		@Override
  		public void onUpdate() {
  			getManager().getCamera().setZoom(getManager().getCamera().getZoom()-0.1);
  		}
  		@Override
  		public void onEnd() {
  		}
  		@Override
  		public void onBegin() {
  			getManager().getCamera().setZoom(getManager().getCamera().getZoom()-0.1);
  		}
  	}, KeyCode.SUBTRACT);
    getInput().addEvent(new UserEvent("Up") {
			@Override
			public void onUpdate() {
			}
			@Override
			public void onEnd() {
				aGameObject.getVelocity().sub(0.0,-0.05);
			}
			@Override
			public void onBegin() {
				aGameObject.getVelocity().add(0.0,-0.05);
			}
		}, KeyCode.Z);
  }

  @Override
	public void initCamera(Camera camera) {
		camera.setPosition(aGameObject.getPosition());
		camera.setGameObjectBinded(aGameObject);
	}

  public void initParticle() {
		Emitter fire = new FireEmitter();
		fire.at(new Point2D(1.0,1.0)).with(8).with(new Point2D(0.0,0.0));
		System.out.println(getManager());
		getManager().getParticuleEngine().addEmitter(fire);
	}

  public static void main(String[] args) {
  		launch(args);
  }

}
```

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Java FX](https://wiki.openjdk.java.net/display/OpenJFX/Main) - The graphical use

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Release History

## Authors

* **Hachard Victor** - *Initial work* - [Glaskani](https://github.com/Glaskani)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
