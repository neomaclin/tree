package org.qq

import scala.annotation.tailrec


package object question {

  case class TreeNode[T](data: T, children: Seq[TreeNode[T]] = Nil)

  def asciiDisplay(root: TreeNode[String]): Seq[String] = {

    def nodeDisplay(node: TreeNode[String],
                    depth: Int = 0,
                    isRoot: Boolean = true,
                    branches: Int = 0): Seq[String] = {

      val data = s"+-${node.data}"
      val counts = if (isRoot) branches - 1 else branches
      val branching = if (branches == 0) "" else "| " * counts
      val (padding, spacing) =
        if (isRoot && depth == 0) ("", "")
        else if (counts == 0) ("", "  " * (depth - counts))
        else ("  ", "  " * (depth - counts - 1))

      val dataSeq = Seq(padding + branching + spacing + data)
      val childrenSeq =
        if (node.children.nonEmpty) childrenDisplay(node.children, depth + 1, branches).distinct
        else Nil

      dataSeq ++ childrenSeq
    }

    @tailrec
    def childrenDisplay(children: Seq[TreeNode[String]],
                        depth: Int = 0,
                        branches: Int = 0,
                        acc: Seq[String] = Seq.empty[String]): Seq[String] = {
      children match {
        case Nil =>
          acc ++ { if (branches > 0) Seq(("  " + "| " * branches).dropRight(1)) else Nil }
        case x :: xs =>
          val expending = x.children.nonEmpty && xs.nonEmpty
          val newBranches = if (expending) branches + 1 else branches
          childrenDisplay(xs, depth, branches, acc ++ nodeDisplay(x, depth, expending, newBranches))
      }

    }

    nodeDisplay(root)
  }
}
