package domain.states;

public enum RoleType {

    // Mando
    COMMANDER,
    EXECUTIVE_OFFICER,
    SQUAD_LEADER,
    FIRE_TEAM_LEADER,

    // Infantería
    RIFLEMAN,
    AUTOMATIC_RIFLEMAN,
    GRENADIER,
    MACHINE_GUNNER,
    MARKSMAN,
    SNIPER,
    SPOTTER,

    // Soporte y logística
    MEDIC,
    COMBAT_ENGINEER,
    EXPLOSIVE_ORDNANCE,
    RADIO_OPERATOR,
    LOGISTICS,

    // Reconocimiento
    SCOUT,
    PATHFINDER,
    FORWARD_OBSERVER,
    EXPLORER,
    INFILTRATOR,
    TRACKER,

    // Operaciones especiales
    OPERATOR,
    COMBAT_DIVER,
    DEMO_EXPERT,

    // Movilidad
    DRIVER,
    PILOT,
    GUNNER,

    // Roles narrativos
    HVT,
    VIP,
    PRISONER,
    CIVILIAN
}