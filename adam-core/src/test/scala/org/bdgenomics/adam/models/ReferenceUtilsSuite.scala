package org.bdgenomics.adam.models

import org.bdgenomics.adam.models.ReferenceUtils._
import org.bdgenomics.adam.util.ADAMFunSuite

class ReferenceUtilsSuite extends ADAMFunSuite {

  test("unionReferenceSet: empty") {
    val regions = Seq()

    assert(unionReferenceSet(regions) === regions)
  }

  test("unionReferenceSet: one region") {
    val regions = Seq(ReferenceRegion("1", 3, 7))

    assert(unionReferenceSet(regions) === regions)
  }

  test("unionReferenceSet: multiple regions on one contig, all overlap") {
    val regions = Seq(ReferenceRegion("1", 3, 7),
      ReferenceRegion("1", 1, 5),
      ReferenceRegion("1", 6, 10))

    assert(unionReferenceSet(regions) === Seq(ReferenceRegion("1", 1, 10)))
  }

  test("unionReferenceSet: multiple regions on one contig, some overlap") {
    val regions = Seq(ReferenceRegion("1", 3, 7),
      ReferenceRegion("1", 1, 5),
      ReferenceRegion("1", 8, 10))

    assert(unionReferenceSet(regions) === Seq(ReferenceRegion("1", 8, 10), ReferenceRegion("1", 1, 7)))
  }

  test("unionReferenceSet: multiple regions on multiple contigs") {
    val regions = Seq(ReferenceRegion("1", 3, 7),
      ReferenceRegion("1", 1, 5),
      ReferenceRegion("2", 4, 8))

    assert(unionReferenceSet(regions) === Seq(ReferenceRegion("2", 4, 8), ReferenceRegion("1", 1, 7)))
  }

}
