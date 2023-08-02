package entities;

public enum EntityType {
    PLAYER(0),
    NPC(1),
    MONSTER(2),
    SWORD(3),
    AXE(4),
    CLUB(5),
    SHIELD(6),
    CONSUMABLE(7),
    CONTAINER(8),
    NECKLACE(9),
    HELMET(10),
    ARMOR(11),
    LEGS(12),
    RING(13),
    PROJECTILES(14),
    BOOTS(15),
    OBSTACLE(16),
    HUD(17),
    KEY(18),
    TOOLS(19),
    EMERALD_SWORD(20),
    PICKUP_ONLY(21),
    NOT_PICKABLE(22);


    private final int value;

    EntityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
