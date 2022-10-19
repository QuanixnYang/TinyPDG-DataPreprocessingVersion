public class A{
    protected String doIt() throws java.lang.Exception {
        StringBuffer sql = null;
        int no = 0;
        String clientCheck = getWhereClause();
        if (m_deleteOldImported) {
            sql = new StringBuffer("DELETE I_BPartner " + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdateEx(sql.toString(), get_TrxName());
            log.fine("Delete Old Impored =" + no);
        }
        sql = new StringBuffer("UPDATE I_BPartner " + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append(")," + " AD_Org_ID = COALESCE (AD_Org_ID, 0)," + " IsActive = COALESCE (IsActive, 'Y')," + " Created = COALESCE (Created, SysDate)," + " CreatedBy = COALESCE (CreatedBy, 0)," + " Updated = COALESCE (Updated, SysDate)," + " UpdatedBy = COALESCE (UpdatedBy, 0)," + " I_ErrorMsg = ' '," + " I_IsImported = 'N' " + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Reset=" + no);
        ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_BEFORE_VALIDATE);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET GroupValue=(SELECT MAX(Value) FROM C_BP_Group g WHERE g.IsDefault='Y'" + " AND g.AD_Client_ID=i.AD_Client_ID) ");
        sql.append("WHERE GroupValue IS NULL AND C_BP_Group_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Group Default=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET C_BP_Group_ID=(SELECT C_BP_Group_ID FROM C_BP_Group g" + " WHERE i.GroupValue=g.Value AND g.AD_Client_ID=i.AD_Client_ID) " + "WHERE C_BP_Group_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Group=" + no);
        sql = new StringBuffer("UPDATE I_BPartner " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Group, ' " + "WHERE C_BP_Group_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.config("Invalid Group=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c" + " WHERE i.CountryCode=c.CountryCode AND c.AD_Client_ID IN (0, i.AD_Client_ID)) " + "WHERE C_Country_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Country=" + no);
        sql = new StringBuffer("UPDATE I_BPartner " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Country, ' " + "WHERE C_Country_ID IS NULL AND (City IS NOT NULL OR Address1 IS NOT NULL)" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.config("Invalid Country=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "Set RegionName=(SELECT MAX(Name) FROM C_Region r" + " WHERE r.IsDefault='Y' AND r.C_Country_ID=i.C_Country_ID" + " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) ");
        sql.append("WHERE RegionName IS NULL AND C_Region_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Region Default=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r" + " WHERE r.Name=i.RegionName AND r.C_Country_ID=i.C_Country_ID" + " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) " + "WHERE C_Region_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Region=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Region, ' " + "WHERE C_Region_ID IS NULL " + " AND EXISTS (SELECT * FROM C_Country c" + " WHERE c.C_Country_ID=i.C_Country_ID AND c.HasRegion='Y')" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.config("Invalid Region=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET C_Greeting_ID=(SELECT C_Greeting_ID FROM C_Greeting g" + " WHERE i.BPContactGreeting=g.Name AND g.AD_Client_ID IN (0, i.AD_Client_ID)) " + "WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Greeting=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Greeting, ' " + "WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.config("Invalid Greeting=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET (C_BPartner_ID,AD_User_ID)=" + "(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u " + "WHERE i.EMail=u.EMail AND u.AD_Client_ID=i.AD_Client_ID) " + "WHERE i.EMail IS NOT NULL AND I_IsImported='N'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Found EMail User=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET C_BPartner_ID=(SELECT C_BPartner_ID FROM C_BPartner p" + " WHERE i.Value=p.Value AND p.AD_Client_ID=i.AD_Client_ID) " + "WHERE C_BPartner_ID IS NULL AND Value IS NOT NULL" + " AND I_IsImported='N'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Found BPartner=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET AD_User_ID=(SELECT AD_User_ID FROM AD_User c" + " WHERE i.ContactName=c.Name AND i.C_BPartner_ID=c.C_BPartner_ID AND c.AD_Client_ID=i.AD_Client_ID) " + "WHERE C_BPartner_ID IS NOT NULL AND AD_User_ID IS NULL AND ContactName IS NOT NULL" + " AND I_IsImported='N'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Found Contact=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET C_BPartner_Location_ID=(SELECT C_BPartner_Location_ID" + " FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)" + " WHERE i.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=i.AD_Client_ID" + " AND (i.Address1=l.Address1 OR (i.Address1 IS NULL AND l.Address1 IS NULL))" + " AND (i.Address2=l.Address2 OR (i.Address2 IS NULL AND l.Address2 IS NULL))" + " AND (i.City=l.City OR (i.City IS NULL AND l.City IS NULL))" + " AND (i.Postal=l.Postal OR (i.Postal IS NULL AND l.Postal IS NULL))" + " AND (i.Postal_Add=l.Postal_Add OR (l.Postal_Add IS NULL AND l.Postal_Add IS NULL))" + " AND i.C_Region_ID=l.C_Region_ID AND i.C_Country_ID=l.C_Country_ID) " + "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL" + " AND I_IsImported='N'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Found Location=" + no);
        sql = new StringBuffer("UPDATE I_BPartner i " + "SET R_InterestArea_ID=(SELECT R_InterestArea_ID FROM R_InterestArea ia " + "WHERE i.InterestAreaName=ia.Name AND ia.AD_Client_ID=i.AD_Client_ID) " + "WHERE R_InterestArea_ID IS NULL AND InterestAreaName IS NOT NULL" + " AND I_IsImported='N'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.fine("Set Interest Area=" + no);
        sql = new StringBuffer("UPDATE I_BPartner " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Value is mandatory, ' " + "WHERE Value IS NULL " + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdateEx(sql.toString(), get_TrxName());
        log.config("Value is mandatory=" + no);
        ModelValidationEngine.get().fireImportValidate(this, null, null, ImportValidator.TIMING_AFTER_VALIDATE);
        commitEx();
        if (p_IsValidateOnly) {
            return "Validated";
        }
        int noInsert = 0;
        int noUpdate = 0;
        sql = new StringBuffer("SELECT * FROM I_BPartner " + "WHERE I_IsImported='N'").append(clientCheck);
        sql.append(" ORDER BY Value, I_BPartner_ID");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
            rs = pstmt.executeQuery();
            String Old_BPValue = "";
            MBPartner bp = null;
            MBPartnerLocation bpl = null;
            while (rs.next()) {
                String New_BPValue = rs.getString("Value");
                X_I_BPartner impBP = new X_I_BPartner(getCtx(), rs, get_TrxName());
                log.fine("I_BPartner_ID=" + impBP.getI_BPartner_ID() + ", C_BPartner_ID=" + impBP.getC_BPartner_ID() + ", C_BPartner_Location_ID=" + impBP.getC_BPartner_Location_ID() + ", AD_User_ID=" + impBP.getAD_User_ID());
                if (!New_BPValue.equals(Old_BPValue)) {
                    bp = null;
                    if (impBP.getC_BPartner_ID() == 0) {
                        if (impBP.getName() == null || impBP.getName().length() == 0) {
                            sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Invalid Name, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                            DB.executeUpdate(sql.toString(), get_TrxName());
                            continue;
                        }
                        bp = new MBPartner(impBP);
                        if (!impBP.get_ValueAsString("AD_Language").equals("")) bp.set_ValueOfColumn("AD_Language", impBP.get_ValueAsString("AD_Language"));
                        if (!impBP.get_ValueAsString("lbr_BPTypeBR").equals("")) bp.set_ValueOfColumn("lbr_BPTypeBR", impBP.get_ValueAsString("lbr_BPTypeBR"));
                        if (!impBP.get_ValueAsString("lbr_CNPJ").equals("")) {
                            Boolean ok = org.adempierelbr.validator.ValidatorBPartner.validaCNPJ(impBP.get_ValueAsString("lbr_CNPJ"));
                            if (ok) bp.set_ValueOfColumn("lbr_CNPJ", impBP.get_ValueAsString("lbr_CNPJ")); else {
                                sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Invalid CNPJ, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                                DB.executeUpdate(sql.toString(), get_TrxName());
                                continue;
                            }
                        }
                        if (!impBP.get_ValueAsString("lbr_CPF").equals("")) {
                            Boolean ok = org.adempierelbr.validator.ValidatorBPartner.validaCPF(impBP.get_ValueAsString("lbr_CPF"));
                            if (ok) bp.set_ValueOfColumn("lbr_CPF", impBP.get_ValueAsString("lbr_CPF")); else {
                                sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Invalid CPF, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                                DB.executeUpdate(sql.toString(), get_TrxName());
                                continue;
                            }
                        }
                        if (!impBP.get_ValueAsString("lbr_IE").equals("")) {
                            bp.set_ValueOfColumn("lbr_IE", impBP.get_ValueAsString("lbr_IE"));
                            bp.set_ValueOfColumn("lbr_IsIEExempt", false);
                        }
                        if (!impBP.get_ValueAsString("lbr_CCM").equals("")) bp.set_ValueOfColumn("lbr_CCM", impBP.get_ValueAsString("lbr_CCM"));
                        if (!impBP.get_ValueAsString("lbr_RG").equals("")) bp.set_ValueOfColumn("lbr_RG", impBP.get_ValueAsString("lbr_RG"));
                        if (!impBP.get_ValueAsString("isVendor").equals("")) bp.set_ValueOfColumn("isVendor", impBP.get_ValueAsString("isVendor"));
                        if (!impBP.get_ValueAsString("isCustomer").equals("")) bp.set_ValueOfColumn("isCustomer", impBP.get_ValueAsString("isCustomer"));
                        if (!impBP.get_ValueAsString("isSalesRep").equals("")) bp.set_ValueOfColumn("isSalesRep", impBP.get_ValueAsString("isSalesRep"));
                        ModelValidationEngine.get().fireImportValidate(this, impBP, bp, ImportValidator.TIMING_AFTER_IMPORT);
                        setTypeOfBPartner(impBP, bp);
                        if (bp.save()) {
                            impBP.setC_BPartner_ID(bp.getC_BPartner_ID());
                            log.finest("Insert BPartner - " + bp.getC_BPartner_ID());
                            noInsert++;
                        } else {
                            sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("(CASE WHEN (SELECT COUNT(*) FROM C_BPartner WHERE lbr_CNPJ IS NOT NULL AND lbr_CNPJ='").append(impBP.get_ValueAsString("lbr_CNPJ")).append("') > 0 THEN 'CNPJ Duplicado, ' WHEN (SELECT COUNT(*) FROM C_BPartner WHERE lbr_CPF IS NOT NULL AND lbr_CPF='").append(impBP.get_ValueAsString("lbr_CPF")).append("') > 0 THEN 'CPF Duplicado, ' END) ||").append("'Cannot Insert BPartner, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                            DB.executeUpdateEx(sql.toString(), get_TrxName());
                            continue;
                        }
                    } else {
                        bp = new MBPartner(getCtx(), impBP.getC_BPartner_ID(), get_TrxName());
                        if (impBP.getName() != null) {
                            bp.setName(impBP.getName());
                            bp.setName2(impBP.getName2());
                        }
                        if (impBP.getDUNS() != null) bp.setDUNS(impBP.getDUNS());
                        if (impBP.getTaxID() != null) bp.setTaxID(impBP.getTaxID());
                        if (impBP.getNAICS() != null) bp.setNAICS(impBP.getNAICS());
                        if (impBP.getDescription() != null) bp.setDescription(impBP.getDescription());
                        if (impBP.getC_BP_Group_ID() != 0) bp.setC_BP_Group_ID(impBP.getC_BP_Group_ID());
                        ModelValidationEngine.get().fireImportValidate(this, impBP, bp, ImportValidator.TIMING_AFTER_IMPORT);
                        if (!impBP.get_ValueAsString("AD_Language").equals("")) bp.set_ValueOfColumn("AD_Language", impBP.get_ValueAsString("AD_Language"));
                        if (!impBP.get_ValueAsString("lbr_BPTypeBR").equals("")) bp.set_ValueOfColumn("lbr_BPTypeBR", impBP.get_ValueAsString("lbr_BPTypeBR"));
                        if (!impBP.get_ValueAsString("lbr_CNPJ").equals("")) {
                            Boolean ok = org.adempierelbr.validator.ValidatorBPartner.validaCNPJ(impBP.get_ValueAsString("lbr_CNPJ"));
                            if (ok) bp.set_ValueOfColumn("lbr_CNPJ", impBP.get_ValueAsString("lbr_CNPJ")); else {
                                sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Invalid CNPJ, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                                DB.executeUpdate(sql.toString(), get_TrxName());
                                continue;
                            }
                        }
                        if (!impBP.get_ValueAsString("lbr_CPF").equals("")) {
                            Boolean ok = org.adempierelbr.validator.ValidatorBPartner.validaCPF(impBP.get_ValueAsString("lbr_CPF"));
                            if (ok) bp.set_ValueOfColumn("lbr_CPF", impBP.get_ValueAsString("lbr_CPF")); else {
                                sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Invalid CPF, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                                DB.executeUpdate(sql.toString(), get_TrxName());
                                continue;
                            }
                        }
                        if (!impBP.get_ValueAsString("lbr_IE").equals("")) bp.set_ValueOfColumn("lbr_IE", impBP.get_ValueAsString("lbr_IE"));
                        if (!impBP.get_ValueAsString("lbr_CCM").equals("")) bp.set_ValueOfColumn("lbr_CCM", impBP.get_ValueAsString("lbr_CCM"));
                        if (!impBP.get_ValueAsString("lbr_RG").equals("")) bp.set_ValueOfColumn("lbr_RG", impBP.get_ValueAsString("lbr_RG"));
                        if (!impBP.get_ValueAsString("isVendor").equals("")) bp.set_ValueOfColumn("isVendor", impBP.get_ValueAsString("isVendor"));
                        if (!impBP.get_ValueAsString("isCustomer").equals("")) bp.set_ValueOfColumn("isCustomer", impBP.get_ValueAsString("isCustomer"));
                        if (!impBP.get_ValueAsString("isSalesRep").equals("")) bp.set_ValueOfColumn("isSalesRep", impBP.get_ValueAsString("isSalesRep"));
                        setTypeOfBPartner(impBP, bp);
                        if (bp.save()) {
                            log.finest("Update BPartner - " + bp.getC_BPartner_ID());
                            noUpdate++;
                        } else {
                            sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Cannot Update BPartner, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                            DB.executeUpdateEx(sql.toString(), get_TrxName());
                            continue;
                        }
                    }
                    bpl = null;
                    if (impBP.getC_BPartner_Location_ID() != 0) {
                        bpl = new MBPartnerLocation(getCtx(), impBP.getC_BPartner_Location_ID(), get_TrxName());
                        MLocation location = new MLocation(getCtx(), bpl.getC_Location_ID(), get_TrxName());
                        location.setC_Country_ID(impBP.getC_Country_ID());
                        location.setC_Region_ID(impBP.getC_Region_ID());
                        location.setCity(impBP.getCity());
                        location.setAddress1(impBP.getAddress1());
                        location.setAddress2(impBP.getAddress2());
                        location.setAddress3(impBP.get_ValueAsString("Address3"));
                        location.setAddress4(impBP.get_ValueAsString("Address4"));
                        location.setPostal(impBP.getPostal());
                        location.setPostal_Add(impBP.getPostal_Add());
                        if (!location.save()) log.warning("Location not updated"); else bpl.setC_Location_ID(location.getC_Location_ID());
                        if (impBP.getPhone() != null) bpl.setPhone(impBP.getPhone());
                        if (impBP.getPhone2() != null) bpl.setPhone2(impBP.getPhone2());
                        if (impBP.getFax() != null) bpl.setFax(impBP.getFax());
                        ModelValidationEngine.get().fireImportValidate(this, impBP, bpl, ImportValidator.TIMING_AFTER_IMPORT);
                        bpl.save();
                    } else if (impBP.getC_Country_ID() != 0 && impBP.getAddress1() != null && impBP.getCity() != null) {
                        MLocation location = new MLocation(getCtx(), impBP.getC_Country_ID(), impBP.getC_Region_ID(), impBP.getCity(), get_TrxName());
                        location.setAddress1(impBP.getAddress1());
                        location.setAddress2(impBP.getAddress2());
                        location.setAddress3(impBP.get_ValueAsString("Address3"));
                        location.setAddress4(impBP.get_ValueAsString("Address4"));
                        location.setPostal(impBP.getPostal());
                        location.setPostal_Add(impBP.getPostal_Add());
                        if (location.save()) log.finest("Insert Location - " + location.getC_Location_ID()); else {
                            rollback();
                            noInsert--;
                            sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Cannot Insert Location, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                            DB.executeUpdateEx(sql.toString(), get_TrxName());
                            continue;
                        }
                        bpl = new MBPartnerLocation(bp);
                        bpl.setC_Location_ID(location.getC_Location_ID());
                        bpl.setPhone(impBP.getPhone());
                        bpl.setPhone2(impBP.getPhone2());
                        bpl.setFax(impBP.getFax());
                        ModelValidationEngine.get().fireImportValidate(this, impBP, bpl, ImportValidator.TIMING_AFTER_IMPORT);
                        if (bpl.save()) {
                            log.finest("Insert BP Location - " + bpl.getC_BPartner_Location_ID());
                            impBP.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
                        } else {
                            rollback();
                            noInsert--;
                            sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Cannot Insert BPLocation, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                            DB.executeUpdateEx(sql.toString(), get_TrxName());
                            continue;
                        }
                    }
                }
                Old_BPValue = New_BPValue;
                MUser user = null;
                if (impBP.getAD_User_ID() != 0) {
                    user = new MUser(getCtx(), impBP.getAD_User_ID(), get_TrxName());
                    if (user.getC_BPartner_ID() == 0) user.setC_BPartner_ID(bp.getC_BPartner_ID()); else if (user.getC_BPartner_ID() != bp.getC_BPartner_ID()) {
                        rollback();
                        noInsert--;
                        sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'BP of User <> BP, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                        DB.executeUpdateEx(sql.toString(), get_TrxName());
                        continue;
                    }
                    if (impBP.getC_Greeting_ID() != 0) user.setC_Greeting_ID(impBP.getC_Greeting_ID());
                    String name = impBP.getContactName();
                    if (name == null || name.length() == 0) name = impBP.getEMail();
                    user.setName(name);
                    if (impBP.getTitle() != null) user.setTitle(impBP.getTitle());
                    if (impBP.getContactDescription() != null) user.setDescription(impBP.getContactDescription());
                    if (impBP.getComments() != null) user.setComments(impBP.getComments());
                    if (impBP.getPhone() != null) user.setPhone(impBP.getPhone());
                    if (impBP.getPhone2() != null) user.setPhone2(impBP.getPhone2());
                    if (impBP.getFax() != null) user.setFax(impBP.getFax());
                    if (impBP.getEMail() != null) user.setEMail(impBP.getEMail());
                    if (impBP.getBirthday() != null) user.setBirthday(impBP.getBirthday());
                    if (bpl != null) user.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
                    ModelValidationEngine.get().fireImportValidate(this, impBP, user, ImportValidator.TIMING_AFTER_IMPORT);
                    if (user.save()) {
                        log.finest("Update BP Contact - " + user.getAD_User_ID());
                    } else {
                        rollback();
                        noInsert--;
                        sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Cannot Update BP Contact, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                        DB.executeUpdateEx(sql.toString(), get_TrxName());
                        continue;
                    }
                } else if (impBP.getContactName() != null || impBP.getEMail() != null) {
                    user = new MUser(bp);
                    if (impBP.getC_Greeting_ID() != 0) user.setC_Greeting_ID(impBP.getC_Greeting_ID());
                    String name = impBP.getContactName();
                    if (name == null || name.length() == 0) name = impBP.getEMail();
                    user.setName(name);
                    user.setTitle(impBP.getTitle());
                    user.setDescription(impBP.getContactDescription());
                    user.setComments(impBP.getComments());
                    user.setPhone(impBP.getPhone());
                    user.setPhone2(impBP.getPhone2());
                    user.setFax(impBP.getFax());
                    user.setEMail(impBP.getEMail());
                    user.setBirthday(impBP.getBirthday());
                    if (bpl != null) user.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
                    ModelValidationEngine.get().fireImportValidate(this, impBP, user, ImportValidator.TIMING_AFTER_IMPORT);
                    if (user.save()) {
                        log.finest("Insert BP Contact - " + user.getAD_User_ID());
                        impBP.setAD_User_ID(user.getAD_User_ID());
                    } else {
                        rollback();
                        noInsert--;
                        sql = new StringBuffer("UPDATE I_BPartner i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append("'Cannot Insert BPContact, ' ").append("WHERE I_BPartner_ID=").append(impBP.getI_BPartner_ID());
                        DB.executeUpdateEx(sql.toString(), get_TrxName());
                        continue;
                    }
                }
                if (impBP.getR_InterestArea_ID() != 0 && user != null) {
                    MContactInterest ci = MContactInterest.get(getCtx(), impBP.getR_InterestArea_ID(), user.getAD_User_ID(), true, get_TrxName());
                    ci.save();
                }
                impBP.setI_IsImported(true);
                impBP.setProcessed(true);
                impBP.setProcessing(false);
                impBP.saveEx();
                commitEx();
            }
            DB.close(rs, pstmt);
        } catch (SQLException e) {
            rollback();
            throw new DBException(e, sql.toString());
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
            sql = new StringBuffer("UPDATE I_BPartner " + "SET I_IsImported='N', Updated=SysDate " + "WHERE I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdateEx(sql.toString(), get_TrxName());
            addLog(0, null, new BigDecimal(no), "@Errors@");
            addLog(0, null, new BigDecimal(noInsert), "@C_BPartner_ID@: @Inserted@");
            addLog(0, null, new BigDecimal(noUpdate), "@C_BPartner_ID@: @Updated@");
        }
        return "";
    }
}