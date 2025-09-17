package listeners;

/**
 * The {@code HitNotifier} interface should be implemented by objects that can be hit,
 * and that want to notify registered {@link HitListener}s when a hit occurs.
 */
public interface HitNotifier {

    /**
     * Adds a {@link HitListener} to the list of listeners to be notified when a hit occurs.
     *
     * @param hl the HitListener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a {@link HitListener} from the list of listeners.
     *
     * @param hl the HitListener to remove.
     */
    void removeHitListener(HitListener hl);
 }