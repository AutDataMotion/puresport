CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`sporterScoreStatis` AS select `u`.`usrid` AS `usrid`,`u`.`usr_tp` AS `usr_tp`,`u`.`usr_nm` AS `usr_nm`,`u`.`nm` AS `nm`,`u`.`crdt_tp` AS `crdt_tp`,`u`.`spt_prj` AS `spt_prj`,`u`.`crdt_no` AS `crdt_no`,`u`.`gnd` AS `gnd`,`u`.`pswd` AS `pswd`,`u`.`brth_dt` AS `brth_dt`,`u`.`adiv_cd` AS `adiv_cd`,`u`.`asscid` AS `asscid`,`u`.`mblph_no` AS `mblph_no`,`u`.`tms` AS `tms`,`u`.`cty_prov_city_mgrid` AS `cty_prov_city_mgrid`,`u`.`rmrk` AS `rmrk`,`u`.`assc_mgrid` AS `assc_mgrid`,`u`.`email` AS `email`,`u`.`bloodtp` AS `bloodtp`,`u`.`ethnct` AS `ethnct`,`u`.`remark` AS `remark`,`u`.`typelevel` AS `typelevel`,`u`.`province` AS `province`,`u`.`city` AS `city`,`u`.`institute` AS `institute`,`u`.`department` AS `department`,`u`.`post` AS `post`,`s`.`examid` AS `examid`,`s`.`exam_nm` AS `exam_nm`,`s`.`exam_grd` AS `exam_grd` from (`puresport`.`t1_usr_bsc` `u` left join `puresport`.`t11_exam_stat` `s` on((`u`.`usrid` = `s`.`usrid`)));

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`prjGroupAnsweredCnt` AS select `p`.`spt_prj` AS `spt_prj`,`p`.`province` AS `province`,`p`.`city` AS `city`,`p`.`institute` AS `institute`,count(1) AS `cnt` from `puresport`.`sporterScoreStatis` `p` where (`p`.`exam_grd` is not null) group by `p`.`spt_prj`,`p`.`province`,`p`.`city`,`p`.`institute`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`prjGroupCnt` AS select `p`.`spt_prj` AS `spt_prj`,`p`.`province` AS `province`,`p`.`city` AS `city`,`p`.`institute` AS `institute`,count(1) AS `cnt` from `puresport`.`sporterScoreStatis` `p` group by `p`.`spt_prj`,`p`.`province`,`p`.`city`,`p`.`institute`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`prjGroupPassedCnt` AS select `p`.`spt_prj` AS `spt_prj`,`p`.`province` AS `province`,`p`.`city` AS `city`,`p`.`institute` AS `institute`,count(1) AS `cnt` from `puresport`.`sporterScoreStatis` `p` where (`p`.`exam_grd` >= 80) group by `p`.`spt_prj`,`p`.`province`,`p`.`city`,`p`.`institute`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`prjGroupStatis` AS select `t`.`spt_prj` AS `spt_prj`,`t`.`province` AS `province`,`t`.`city` AS `city`,`t`.`institute` AS `institute`,`t`.`cnt` AS `cnt_total`,`a`.`cnt` AS `cnt_answered`,`p`.`cnt` AS `cnt_passed` from ((`puresport`.`prjGroupCnt` `t` left join `puresport`.`prjGroupAnsweredCnt` `a` on(((`t`.`spt_prj` = `a`.`spt_prj`) and (`t`.`province` = `a`.`province`) and (`t`.`city` = `a`.`city`) and (`t`.`institute` = `a`.`institute`)))) left join `puresport`.`prjGroupPassedCnt` `p` on(((`t`.`spt_prj` = `p`.`spt_prj`) and (`t`.`province` = `p`.`province`) and (`t`.`city` = `p`.`city`) and (`t`.`institute` = `p`.`institute`))));

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`problemCnt` AS select `puresport`.`t10_exam_grd`.`prblmid` AS `prblmid`,count(1) AS `cnt` from `puresport`.`t10_exam_grd` group by `puresport`.`t10_exam_grd`.`prblmid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`problemWrongCnt` AS select `puresport`.`t10_exam_grd`.`prblmid` AS `prblmid`,count(1) AS `cnt` from `puresport`.`t10_exam_grd` where (`puresport`.`t10_exam_grd`.`result` = '错误') group by `puresport`.`t10_exam_grd`.`prblmid`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `puresport`.`problemStatis` AS select `b`.`prblmid` AS `prblmid`,`b`.`prblm_ppl` AS `prblm_ppl`,`b`.`prblm_tp` AS `prblm_tp`,`b`.`opt` AS `opt`,`b`.`ttl` AS `ttl`,`b`.`prblm_aswr` AS `prblm_aswr`,`b`.`scor` AS `scor`,`b`.`tms` AS `tms`,`ca`.`cnt` AS `cntall`,`cw`.`cnt` AS `cntwrong` from ((`puresport`.`t9_tstlib` `b` left join `puresport`.`problemCnt` `ca` on((`b`.`prblmid` = `ca`.`prblmid`))) left join `puresport`.`problemWrongCnt` `cw` on((`b`.`prblmid` = `cw`.`prblmid`))) order by `cw`.`cnt` desc;









