import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        boolean isFree = (boolean) table.getValueAt(row, 2);

        if(!isFree)
            c.setBackground(new Color(0xef476f));
        else
            c.setBackground(new Color(0x06d6a0));

        return c;
    }
}
