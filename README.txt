Setup Env:
export GLOBUS_LOCATION=/h1/ludetc/cadsrGridDataService/globus/globus-grid12/ws-core-4.0.3
export JBOSS_HOME=/usr/local/jboss404/

Shutdown Jboss
cd $JBOSS_HOME
./stop_jboss


Grid Deploy
ant globus-jboss-install -Djboss.dir=$JBOSS_HOME -Djboss.server=ncicb-59480 -Dhostname=cbvapp-d1008.nci.nih.gov -Dport=59480


Deploy the APP:
ant -Djboss.server=ncicb-59480 -Dcadsrapi.loc=cadsrapi-qa.nci.nih.gov service-jboss-deploy  -Dindex.location=cbiovdev5012.nci.nih.gov:8080


Deploy ALL:
ant -Dcadsrapi.loc=cadsrapi-qa.nci.nih.gov  -Dindex.location=cbiovdev5012.nci.nih.gov:8080  -Dport=59480  -Djboss.dir=$JBOSS_HOME  -Dhostname=cbvapp-d1008.nci.nih.gov all-jboss 


UnDeploy
ant -Djboss.server=ncicb-59480  -Djboss.dir=$JBOSS_HOME undeploy


Start JBoss
./start_jboss


Run the tesT:
ant -f queryRunnerExec.xml -Dservice.url=http://cbvapp-d1008:59480/wsrf/services/cagrid/CaDSRDataService -Dcql.file=exampleCqlQuery.xml



Check JBoss log:
tail -f /usr/local/jboss-4.0.4.GA/server/ncicb-59480/log/server.log


Check Portal:
 dev : http://cbiovdev5017.nci.nih.gov:8080
note: It took 1 hour for the portal to show my service.


