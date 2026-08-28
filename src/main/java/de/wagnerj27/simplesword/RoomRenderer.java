package de.wagnerj27.simplesword;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoomRenderer {

    private final Room room;
    private final Canvas canvas;
    private final int tileSize;
    private final GraphicsContext graphicsContext;
    private final Player player;

    // Creates a renderer for the given room, canvas and player.
    public RoomRenderer(Room room, Canvas canvas, int tileSize, Player player) {
        this.room = room;
        this.canvas = canvas;
        this.tileSize = tileSize;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.player = player;
    }

    // Returns the horizontal component of the direction the player is facing.
    private int getForwardX() {
        switch (player.getDirection()) {
            case NORTH:
            case SOUTH:
                return 0;

            case EAST:
                return 1;

            case WEST:
                return -1;

            default:
                return 0;
        }
    }

    // Returns the vertical component of the direction the player is facing.
    private int getForwardY() {
        switch (player.getDirection()) {
            case NORTH:
                return -1;

            case SOUTH:
                return 1;

            case EAST:
            case WEST:
                return 0;

            default:
                return 0;
        }
    }

    // Returns the horizontal component of the direction to the player's left.
    private int getLeftX() {
        switch (player.getDirection()) {
            case NORTH:
                return -1;

            case SOUTH:
                return 1;

            case EAST:
            case WEST:
                return 0;

            default:
                return 0;
        }
    }

    // Returns the vertical component of the direction to the player's left.
    private int getLeftY() {
        switch (player.getDirection()) {
            case EAST:
                return -1;

            case WEST:
                return 1;

            case NORTH:
            case SOUTH:
                return 0;

            default:
                return 0;
        }
    }

    // Returns the horizontal component of the direction to the player's right.
    private int getRightX() {
        switch (player.getDirection()) {
            case NORTH:
                return 1;

            case SOUTH:
                return -1;

            case EAST:
            case WEST:
                return 0;

            default:
                return 0;
        }
    }

    // Returns the vertical component of the direction to the player's right.
    private int getRightY() {
        switch (player.getDirection()) {
            case EAST:
                return 1;

            case WEST:
                return -1;

            case NORTH:
            case SOUTH:
                return 0;

            default:
                return 0;
        }
    }

    // Checks whether the given room coordinates contain a wall.
    private boolean isWall(int x, int y) {
        return room.isInside(x, y)
                && room.getTile(x, y) instanceof Wall;
    }

    // Calculates the distance from the player to the first wall in front of them.
    private int getWallDistance() {
        Position position = player.getPosition();

        int x = position.getX() + getForwardX();
        int y = position.getY() + getForwardY();

        int distance = 0;

        while (room.isInside(x, y)) {
            distance++;

            if (isWall(x, y)) {
                break;
            }

            x += getForwardX();
            y += getForwardY();
        }

        return distance;
    }

    // Returns the position of the first wall directly in front of the player.
    private Position getFrontWallPosition() {
        Position position = player.getPosition();

        int x = position.getX() + getForwardX();
        int y = position.getY() + getForwardY();

        while (room.isInside(x, y)) {
            if (isWall(x, y)) {
                return new Position(x, y);
            }

            x += getForwardX();
            y += getForwardY();
        }

        return null;
    }

    // Returns the perspective scale for a given wall distance.
    private double getPerspectiveScale(int distance) {
        return 1.0 / (distance + 1.0);
    }

    // Returns the number of consecutive walls starting from the given position.
    private int getWallLength(int startX, int startY, int directionX, int directionY) {
        int length = 0;

        int x = startX;
        int y = startY;

        while (room.isInside(x, y) && isWall(x, y)) {
            length++;

            x += directionX;
            y += directionY;
        }

        return length;
    }

    // Returns the number of consecutive wall tiles beside the front wall.
    private int getFrontWallSideLength(Position wallPosition, boolean left) {
        int directionX = left ? getLeftX() : getRightX();
        int directionY = left ? getLeftY() : getRightY();

        int startX = wallPosition.getX() + directionX;
        int startY = wallPosition.getY() + directionY;

        return getWallLength(
                startX,
                startY,
                directionX,
                directionY
        );
    }

    // Returns the number of consecutive wall tiles directly beside the player.
    private int getPlayerSideWallLength(boolean left) {
        Position position = player.getPosition();

        int directionX = left ? getLeftX() : getRightX();
        int directionY = left ? getLeftY() : getRightY();

        int startX = position.getX() + directionX;
        int startY = position.getY() + directionY;

        return getWallLength(
                startX,
                startY,
                getForwardX(),
                getForwardY()
        );
    }

    // Checks whether a wall is directly to the player's left.
    private boolean hasWallLeft() {
        Position position = player.getPosition();

        int x = position.getX() + getLeftX();
        int y = position.getY() + getLeftY();

        return isWall(x, y);
    }

    // Checks whether a wall is directly to the player's right.
    private boolean hasWallRight() {
        Position position = player.getPosition();

        int x = position.getX() + getRightX();
        int y = position.getY() + getRightY();

        return isWall(x, y);
    }

    // Calculates the horizontal endpoint of a side wall connected to the front wall.
    private double getSideWallEndX(
            double wallX,
            double centerX,
            double outerX,
            int wallLength,
            int distance,
            boolean left) {

        double totalWidth = Math.abs(outerX - centerX);

        double tileWidth =
                totalWidth / Math.max(1, distance + 1);

        double extension = tileWidth * wallLength;

        double result;

        if (left) {
            result = wallX - extension;
            result = Math.max(0, result);
        } else {
            result = wallX + extension;
            result = Math.min(canvas.getWidth(), result);
        }

        return result;
    }

    // Draws the front wall and any wall segments connected directly to it.
    private void drawFrontWall(int distance) {

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double outerMargin = canvasWidth * 0.125;
        double outerTopY = canvasHeight * 0.15;
        double outerBottomY = canvasHeight * 0.85;

        double outerLeftX = outerMargin;
        double outerRightX = canvasWidth - outerMargin;

        double centerX = canvasWidth / 2;
        double centerY = canvasHeight / 2;

        double scale = getPerspectiveScale(distance);

        double wallLeftX =
                centerX + (outerLeftX - centerX) * scale;

        double wallRightX =
                centerX + (outerRightX - centerX) * scale;

        double wallTopY =
                centerY + (outerTopY - centerY) * scale;

        double wallBottomY =
                centerY + (outerBottomY - centerY) * scale;

        double wallWidth = wallRightX - wallLeftX;
        double wallHeight = wallBottomY - wallTopY;

        graphicsContext.setStroke(Color.WHITE);

        // Draws the main front wall.
        graphicsContext.strokeRect(
                wallLeftX,
                wallTopY,
                wallWidth,
                wallHeight
        );

        Position frontWall = getFrontWallPosition();

        if (frontWall == null) {
            return;
        }

        int leftLength =
                getFrontWallSideLength(frontWall, true);

        int rightLength =
                getFrontWallSideLength(frontWall, false);

        // Draws the wall section connected to the left side of the front wall.
        if (leftLength > 0) {

            double leftEndX =
                    getSideWallEndX(
                            wallLeftX,
                            centerX,
                            outerLeftX,
                            leftLength,
                            distance,
                            true
                    );

            // Draws the upper boundary of the left extension.
            graphicsContext.strokeLine(
                    wallLeftX,
                    wallTopY,
                    leftEndX,
                    wallTopY
            );

            // Draws the lower boundary of the left extension.
            graphicsContext.strokeLine(
                    wallLeftX,
                    wallBottomY,
                    leftEndX,
                    wallBottomY
            );

            // Draws the outer edge of the left extension.
            graphicsContext.strokeLine(
                    leftEndX,
                    wallTopY,
                    leftEndX,
                    wallBottomY
            );
        }

        // Draws the wall section connected to the right side of the front wall.
        if (rightLength > 0) {

            double rightEndX =
                    getSideWallEndX(
                            wallRightX,
                            centerX,
                            outerRightX,
                            rightLength,
                            distance,
                            false
                    );

            // Draws the upper boundary of the right extension.
            graphicsContext.strokeLine(
                    wallRightX,
                    wallTopY,
                    rightEndX,
                    wallTopY
            );

            // Draws the lower boundary of the right extension.
            graphicsContext.strokeLine(
                    wallRightX,
                    wallBottomY,
                    rightEndX,
                    wallBottomY
            );

            // Draws the outer edge of the right extension.
            graphicsContext.strokeLine(
                    rightEndX,
                    wallTopY,
                    rightEndX,
                    wallBottomY
            );
        }
    }

    // Draws the left side wall next to the player.
    private void drawLeftWall(int distance) {

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double outerMargin = canvasWidth * 0.125;

        double outerTopY = canvasHeight * 0.15;
        double outerBottomY = canvasHeight * 0.85;

        double outerLeftX = outerMargin;

        double centerX = canvasWidth / 2;
        double centerY = canvasHeight / 2;

        int wallLength = getPlayerSideWallLength(true);

        if (wallLength <= 0) {
            return;
        }

        int endDistance =
                Math.min(wallLength + 1, distance);

        double endScale =
                getPerspectiveScale(endDistance);

        double endLeftX =
                centerX +
                (outerLeftX - centerX) * endScale;

        double endTopY =
                centerY +
                (outerTopY - centerY) * endScale;

        double endBottomY =
                centerY +
                (outerBottomY - centerY) * endScale;

        graphicsContext.setStroke(Color.WHITE);

        // Draws the upper boundary of the left side wall.
        graphicsContext.strokeLine(
                outerLeftX,
                outerTopY,
                endLeftX,
                endTopY
        );

        // Draws the lower boundary of the left side wall.
        graphicsContext.strokeLine(
                outerLeftX,
                outerBottomY,
                endLeftX,
                endBottomY
        );

        // Draws the visible end of the left side wall.
        graphicsContext.strokeLine(
                endLeftX,
                endTopY,
                endLeftX,
                endBottomY
        );
    }

    // Draws the right side wall next to the player.
    private void drawRightWall(int distance) {

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double outerMargin = canvasWidth * 0.125;

        double outerTopY = canvasHeight * 0.15;
        double outerBottomY = canvasHeight * 0.85;

        double outerRightX =
                canvasWidth - outerMargin;

        double centerX = canvasWidth / 2;
        double centerY = canvasHeight / 2;

        int wallLength = getPlayerSideWallLength(false);

        if (wallLength <= 0) {
            return;
        }

        int endDistance =
                Math.min(wallLength + 1, distance);

        double endScale =
                getPerspectiveScale(endDistance);

        double endRightX =
                centerX +
                (outerRightX - centerX) * endScale;

        double endTopY =
                centerY +
                (outerTopY - centerY) * endScale;

        double endBottomY =
                centerY +
                (outerBottomY - centerY) * endScale;

        graphicsContext.setStroke(Color.WHITE);

        // Draws the upper boundary of the right side wall.
        graphicsContext.strokeLine(
                outerRightX,
                outerTopY,
                endRightX,
                endTopY
        );

        // Draws the lower boundary of the right side wall.
        graphicsContext.strokeLine(
                outerRightX,
                outerBottomY,
                endRightX,
                endBottomY
        );

        // Draws the visible end of the right side wall.
        graphicsContext.strokeLine(
                endRightX,
                endTopY,
                endRightX,
                endBottomY
        );
    }

    // Renders the complete room scene.
    public void render() {

        // Clears the previous frame.
        graphicsContext.clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        // Draws the black background.
        graphicsContext.setFill(Color.BLACK);

        graphicsContext.fillRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        // Calculates the distance to the front wall.
        int distance = getWallDistance();

        // Draws the front wall.
        drawFrontWall(distance);

        // Draws the left side wall when one is directly beside the player.
        if (hasWallLeft()) {
            drawLeftWall(distance);
        }

        // Draws the right side wall when one is directly beside the player.
        if (hasWallRight()) {
            drawRightWall(distance);
        }

        // Draws the red canvas border for debugging.
        graphicsContext.setStroke(Color.RED);

        graphicsContext.strokeRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );
    }
}