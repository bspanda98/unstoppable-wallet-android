package io.horizontalsystems.bankwallet.modules.guideview

import android.text.SpannableStringBuilder
import android.text.Spanned
import io.noties.markwon.Markwon
import org.commonmark.node.*

class GuideVisitor(private val markwon: Markwon) : AbstractVisitor() {

    val blocks = mutableListOf<GuideBlock>()

    val spannableStringBuilder = SpannableStringBuilder()

    override fun visit(heading: Heading) {
        val guideVisitor = GuideVisitor(markwon)
        guideVisitor.visitChildren(heading)

        val block = when (heading.level) {
            1 -> GuideBlock.Heading1(guideVisitor.spannableStringBuilder)
            2 -> GuideBlock.Heading2(guideVisitor.spannableStringBuilder)
            else -> null
        }

        block?.let {
            blocks.add(block)
        }
    }

    override fun visit(paragraph: Paragraph) {
        val guideVisitor = GuideVisitor(markwon)
        guideVisitor.visitChildren(paragraph)

        blocks.add(GuideBlock.Paragraph(guideVisitor.spannableStringBuilder))
    }

    override fun visit(text: Text) {
        spannableStringBuilder.append(markwon.render(text))
    }

    override fun visit(strongEmphasis: StrongEmphasis) {
        spannableStringBuilder.append(markwon.render(strongEmphasis))
    }

    override fun visit(emphasis: Emphasis) {
        spannableStringBuilder.append(markwon.render(emphasis))
    }


}

sealed class GuideBlock {
    data class Heading1(val text: Spanned) : GuideBlock()
    data class Heading2(val text: Spanned) : GuideBlock()
    data class Paragraph(val text: Spanned) : GuideBlock()

}
