package com.example.order;

/**
 * Cycle de vie d'une commande e-commerce.
 * Les transitions autorisées sont décrites dans {@link #canTransitionTo}.
 */
public enum OrderStatus {

    /** Commande créée mais pas encore payée. */
    PENDING("En attente", true),

    /** Paiement reçu et validé. */
    PAID("Payée", true),

    /** En cours de préparation en entrepôt. */
    PROCESSING("En traitement", true),

    /** Colis remis au transporteur. */
    SHIPPED("Expédiée", true),

    /** Colis livré au client. */
    DELIVERED("Livrée", false),

    /** Commande annulée (avant expédition). */
    CANCELLED("Annulée", false),

    /** Retour en cours ou effectué. */
    RETURNED("Retournée", false);

    private final String  label;
    private final boolean mutable;

    OrderStatus(String label, boolean mutable) {
        this.label   = label;
        this.mutable = mutable;
    }

    /** Libellé français affiché à l'utilisateur. */
    public String label() { return label; }

    /** Indique si la commande peut encore changer d'état. */
    public boolean isMutable() { return mutable; }

    /**
     * Vérifie qu'une transition vers {@code next} est autorisée.
     *
     * @param next le statut cible
     * @return {@code true} si la transition est valide
     */
    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case PENDING    -> next == PAID || next == CANCELLED;
            case PAID       -> next == PROCESSING || next == CANCELLED;
            case PROCESSING -> next == SHIPPED || next == CANCELLED;
            case SHIPPED    -> next == DELIVERED || next == RETURNED;
            default         -> false;
        };
    }
}
