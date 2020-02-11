package signature

import java.util.*

enum class Letters(val keyVal: String, val chars: String) {
    ATOP("at", "____"), AMID("am", "|__|"), ABOT("ab", "|  |"),
    BTOP("bt", "___ "), BMID("bm", "|__]"), BBOT("bb", "|__]"),
    CTOP("ct", "____"), CMID("cm", "|   "), CBOT("cb", "|___"),
    DTOP("dt", "___ "), DMID("dm", "|  \\"), DBOT("db", "|__/"),
    ETOP("et", "____"), EMID("em", "|___"), EBOT("eb", "|___"),
    FTOP("ft", "____"), FMID("fm", "|___"), FBOT("fb", "|   "),
    GTOP("gt", "____"), GMID("gm", "| __"), GBOT("gb", "|__]"),
    HTOP("ht", "_  _"), HMID("hm", "|__|"), HBOT("hb", "|  |"),
    ITOP("it", "_"), IMID("im", "|"), IBOT("ib", "|"),
    JTOP("jt", "_"), JMID("jm", "|"), JBOT("jb", "_|"),
    KTOP("kt", "_  _"), KMID("km", "|_/ "), KBOT("kb", "| \\_"),
    LTOP("lt", "_   "), LMID("lm", "|   "), LBOT("lb", "|___"),
    MTOP("mt", "_  _"), MMID("mm", "|\\/|"), MBOT("mb", "|  |"),
    NTOP("nt", "_  _ "), NMID("nm", "|\\ | "), NBOT("nb", "| \\| "),
    OTOP("ot", "____"), OMID("om", "|  |"), OBOT("ob", "|__|"),
    PTOP("pt", "___ "), PMID("pm", "|__]"), PBOT("pb", "|   "),
    QTOP("qt", "____"), QMID("qm", "|  |"), QBOT("qb", "|_\\|"),
    RTOP("rt", "____"), RMID("rm", "|__/"), RBOT("rb", "|  \\"),
    STOP("st", "____"), SMID("sm", "[__ "), SBOT("sb", "___]"),
    TTOP("tt", "___"), TMID("tm", " | "), TBOT("tb", " | "),
    UTOP("ut", "_  _"), UMID("um", "|  |"), UBOT("ub", "|__|"),
    VTOP("vt", "_  _"), VMID("vm", "|  |"), VBOT("vb", " \\/ "),
    WTOP("wt", "_ _ _"), WMID("wm", "| | |"), WBOT("wb", "|_|_|"),
    XTOP("xt", "_  _"), XMID("xm", " \\/ "), XBOT("xb", "_/\\_"),
    YTOP("yt", "_   _"), YMID("ym", " \\_/ "), YBOT("yb", "  |  "),
    ZTOP("zt", "___ "), ZMID("zm", "  /"), ZBOT("zb", " /__");

    companion object {
        fun findValue(keyVal: String): String {
            var output = ""
            for (enum in Letters.values()) {
                if (keyVal == enum.keyVal) {
                    output = enum.chars
                }
            }
            return output
        }
    }
}

class SignatureBuilder(firstName: String, lastName: String, val status: String) {
    private val fullName = "${firstName.toUpperCase()} ${lastName.toUpperCase()}"
    var borderWidth = 0

    fun buildString(rowNumber: Int): String {
        var rowString = "*  "
        var rowIdentifier = ""
        when (rowNumber) {
            1 -> rowIdentifier = "t"
            2 -> rowIdentifier = "m"
            3 -> rowIdentifier = "b"
        }

        fullName.forEach { i ->
            if (i == ' ') {
                rowString += "     "
            } else {
                val char = "${i.toLowerCase()}$rowIdentifier"
                rowString += Letters.findValue(char)
                rowString += " "
            }
        }
        rowString += " *"
        borderWidth = if (status.length + 6 > rowString.length) status.length + 6 else rowString.length
        return rowString
    }

    fun borderBuilder(): String {
        var border = ""
        repeat(borderWidth) {
            border += "*"
        }
        return border
    }

    fun statusRowBuilder(): String {
        var statusBorderWidth = borderWidth - (2 + status.length)
        var statusBorderLeft = 0
        var statusBorderRight = 0
        var statusRow = ""
        if (statusBorderWidth % 2 != 0) {
            statusBorderLeft = (statusBorderWidth - 1) / 2
            statusBorderRight = ((statusBorderWidth - 1) / 2) + 1
        } else {
            statusBorderLeft = statusBorderWidth / 2
            statusBorderRight = statusBorderWidth / 2
        }
        if ((status.length % 2 != 0) && (statusBorderWidth % 2 == 0)) {
            statusBorderLeft -= 1
            statusBorderRight += 1
        }
        statusRow += "*"
        repeat(statusBorderLeft) {
            statusRow += " "
        }
        statusRow += status
        repeat(statusBorderRight) {
            statusRow += " "
        }
        statusRow += "*"
        return statusRow
    }



    fun printSignature() {
        val nameTopRow = buildString(1)
        val nameMidRow = buildString(2)
        val nameBottomRow = buildString(3)
        val border = borderBuilder()
        val statusRow = statusRowBuilder()
        println(border)
        println(nameTopRow)
        println(nameMidRow)
        println(nameBottomRow)
        println(statusRow)
        println(border)
    }
}


fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter Your First Name: ")
    val firstName = scanner.next()
    println("Enter Your Last Name: ")
    val lastName = scanner.next()
    println("Enter Your Title: ")
    val statusInput = readLine()!!
    val status = statusInput.toUpperCase()
    val builder = SignatureBuilder(firstName, lastName, status)
    builder.printSignature()
}